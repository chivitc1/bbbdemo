package inet

import org.hibernate.criterion.CriteriaSpecification

class BbbDemoTagLib {
    static namespace = "bbb"

    def userlist = {
        def users = User.list()
        def count = User.count()
        out << render(template: "user/userlistTemplate", model: [users: users, count: count])
    }

//    def roomlist = {
//        def rooms = Room.list()
//        def count = Room.count()
//        out << render(template: "room/roomlistTemplate", model: [rooms: rooms, count: count])
//    }

    def courselist = {
        def courses = Course.list()
        def count = Course.count()
        out << render(template: "course/courselistTemplate", model: [courses: courses, count: count])
    }

    def toggleRoom = {attrs ->

    }

    def teacherCourses = {
        def user = session?.user
        if(!user) {
            out << render(template: "/user/teacher", model: [teacherCourseList:[]])
            return
        }
        def teacherCourseList = Course.findAllByTeacher(user)
        out << render(template: "/user/teacher", model: [teacherCourseList: teacherCourseList?:[]])
    }

    def registeringCourses = {
        def user = session.user
        if(!user) {
            out << render(template: "/user/registeringCourse", model: [registeringCourseList:[]])
            return
        }

        def registeringCourseList = Course.createCriteria().list {
            createAlias(
                    "teacher",
                    "t",
                    CriteriaSpecification.LEFT_JOIN
            )
            or {
                isNull('t.id')
                ne('t.loginId', user.loginId)
            }
        }
        out << render(template: "/user/registeringCourse", model: [registeringCourseList: registeringCourseList?:[]])
    }
}
