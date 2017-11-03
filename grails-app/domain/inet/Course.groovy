package inet

import org.hibernate.criterion.CriteriaSpecification

class Course {

    String name
    String description
    Date startingTime
    User teacher
//    Room room

    static hasMany = [attendees: User]
    static hasOne = [room: Room]

    static constraints = {
        name blank: false
        description nullable: true
        startingTime nullable: true
        teacher nullable: true
        room nullable: true
        attendees nullable: true
    }
    static belongsTo = [room: Room]
//    static mappedBy = [ room: "name", teacher: "fullName" ]

    static hasAttendee(loginId, courseId) {
        def course = Course.get(courseId)

        if (course && course.attendees && course.attendees*.loginId.contains(loginId)){
            return true
        }
        return false
    }

    static String getRole(loginId, Course course) {
        if(course?.teacher?.loginId == loginId)
            return UtilService.ROLE_MODERATOR
        else if(course?.attendees*.loginId.contains(loginId))
            return UtilService.ROLE_ATTENDEE
        else
            return "ROLE_GUEST"
    }

    static getAvailableTeachers(){
        def teachers = User.list()
        return teachers
    }

    static getAvailableRooms(){
        def rooms = Room.createCriteria().list {

            createAlias(
                    "course",
                    "c",
                    CriteriaSpecification.LEFT_JOIN
            )
            and{
                isNull('c.id')
            }
        }
        return rooms
    }

}

//Course.metaClass.hasAttendee << { int loginId ->
//
//    if (attendees*.loginId.contains(loginId)){
//        return true
//    }
//    return false
//}

