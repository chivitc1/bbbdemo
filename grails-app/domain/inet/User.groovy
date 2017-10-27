package inet

class User {
    String loginId
    String password
    String fullName
    String inMeeting
    boolean isAdmin

    static constraints = {
        loginId unique: true
        password()
        fullName blank: false
        inMeeting nullable: true

    }

    String toString() {
        return fullName
    }
}
