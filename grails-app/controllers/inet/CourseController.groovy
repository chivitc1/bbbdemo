package inet

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CourseController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Course.list(params), model:[courseCount: Course.count()]
    }

    def show(Course course) {
        respond course
    }

    def create() {
        respond new Course(params)
    }

    @Transactional
    def save(Course course) {
        if (course == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (course.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond course.errors, view:'create'
            return
        }

        course.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect course
            }
            '*' { respond course, [status: CREATED] }
        }
    }

    def edit(Course course) {
        respond course
    }

    @Transactional
    def update(Course course) {
        if (course == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (course.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond course.errors, view:'edit'
            return
        }

        course.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect course
            }
            '*'{ respond course, [status: OK] }
        }
    }

    @Transactional
    def delete(Course course) {

        if (course == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        course.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Transactional
    def register(int courseId) {
        def user = session.user
        if(!user) {
            flash.registerMessage = "Exception: you not loggined yet, no permission"
            return
        }
        def course = Course.get(courseId)
        if(!course){
            flash.registerMessage = "Exception: course not found"
            return
        }
        if(course.attendees.contains(user) || course?.teacher?.loginId == user?.loginId){
            flash.registerMessage = "You already in the list"
            return
        }
        course.addToAttendees(user)
        course.save(flush: true)
        flash.registerMessage = "Register success"
        redirect(uri: "/")
    }

    def joinRoom() {
        def course = Course.get(params.courseId)
        def user = session.user
        if(!course?.room || !user) {
            throw new Exception("BBB: Course not have room or user not login")
        }

        def role = Course.getRole(user?.loginId, course)
        redirect controller:"bbbCall", action: "joinRoom", params: [roomId: course?.room.id, role: role]
    }
}
