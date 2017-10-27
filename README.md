# bbbdemo
This is demo landing page to test calling BigBlueButton API

Need a running BBB server (currently 1.1)
Change bbbBaseUrl and share secret in build.gradle
Run web app in port 8080 or whatever to serve end user a web page: grails run-app
Change DB config in application.yml, you can use H2 (currently I config mysql as dev environment)

To test:
- Navigate to landing page: http://localhost:8080
- Manage room: create some rooms
- Manage course: create some course, edit to asign room, teacher user
- Then try to login with some users (user1, user2, user3, user4 pass 123) ==> Link to register to a course and join a course will appear.
