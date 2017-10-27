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
    def register() {

    }

}
