package inet

class Room {

    String name
    Date startTime
    String welcome
    String moderatorPW = "654321"
    String attendeePW = "123456"
    Boolean running = false
    Course course

    static hasMany = [attendees: User]

    static constraints = {
        name blank: false
        startTime nullable: true
        welcome nullable: true
        attendees nullable: true
        course nullable: true
    }

    String toString() {
        return name
    }

}
