package inet

class LoginController {

    def login() {
        def user = User.findByLoginId(params.loginId)
        if(user && user.password == params.password) {
            session.user = user
            if (params.cName)
                redirect controller: params.cName, action: params.aName
            else
                redirect uri: '/'
        } else {
            flash.message = "Invalid username or password!"
            render view: 'login'
        }

    }
    def logout() {
        session.user = null
        redirect(uri: '/')

    }
    def registerForm() {
        render view: "registerForm", model: [user: new AccountCommand()]
    }

    def register(AccountCommand ac) {

        if(ac.hasErrors()) {
            render view: "registerForm", model: [ user : ac ]
        }else {
            def user = new User(ac.properties)
            if (user.validate() && user.save()) {
                flash.message = "Welcome aboard, ${ac.fullName ?: ac.loginId}"
                session.user = user
                redirect(uri: '/')
            }else {
                // maybe not unique loginId?
                flash.message = "login ID already taken by someone else!"
                render view: "registerForm", model: [ user : ac ]
            }
        }
    }

}

class AccountCommand {
    String loginId
    String password
    String passwordRepeat
    String fullName

    static constraints = {
        importFrom User
        loginId(matches: "[a-zA-Z0-9]+([a-zA-Z0-9])*",
            validator: {id2 -> return !User.find { loginId == id2}

            })
        passwordRepeat(nullable: false,
                validator: { passwd2, ac ->
                    return passwd2 == ac.password
                })
    }
}
