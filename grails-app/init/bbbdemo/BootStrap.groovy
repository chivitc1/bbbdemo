package inet

import grails.core.GrailsApplication

class BootStrap {
    GrailsApplication grailsApplication

    def init = { servletContext ->
        environments {
            production {
                if (!User.count()) {
                    createSampleData()
                }
            }
            development {
                System.out.print(grailsApplication.config.getProperty('grails.gorm.failOnError'))
                if (!User.count()) {
                    createSampleData()
                }
            }
            test {
                if (!User.count()) createSampleData()
            }
        }
    }

    def destroy = {
    }

    private createSampleData() {
        println "Creating sample data"
        def u1 = new User(loginId: "user1", password: "123", fullName: "Nguyen Van A").save()
        def u2 = new User(loginId: "user2", password: "123", fullName: "Nguyen Van B").save()
        def u3 = new User(loginId: "user3", password: "123", fullName: "Nguyen Van C").save()
        def u4 = new User(loginId: "user4", password: "123", fullName: "Nguyen Van D").save()
        def admin = new User(loginId: "admin", password: "123", isAdmin: true, fullName: "Nguyen Van ADMIN").save()

        def c1 = new Course(name: "Yoga with Yumi").save()
        def c2 = new Course(name: "Marketing for beginers").save()

        def r1 = new Room(name: "First Room").save()
//        def r2 = new Room(name: "Room 2").save()
    }
}
