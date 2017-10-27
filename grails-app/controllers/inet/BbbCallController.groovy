package inet

import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
//import static groovyx.net.http.Method.*


class BbbCallController {

    def bbbCallService
    def createRoom(int roomId) {
//        room = Room.get(1)
        def room = Room.get(roomId)
        bbbCallService.createRoom(room)

        redirect controller: 'room', action: 'index'
    }

    def getRoomInfo(String meetingID) {
        def meetingID2 = Room.get(1).id.toString()
        bbbCallService.getRoomInfo(meetingID2)
    }

    def getRoomList() {
        bbbCallService.getRoomList()
    }
    def joinRoom(int roomId, String role) {
        def user = session.user
        if(!user) {
            flash.message = "Not permitted. You have not loggined"
            redirect(url: "/room/index")
            return
        }

        def room = Room.get(roomId)
        def joinUrl = bbbCallService.joinRoom(room, user, role)
        redirect(url: joinUrl)
    }

    def isRoomRunning(Room room) {
        return bbbCallService.checkRoomRunning(room)
    }

    def closeRoom(int roomId){
        def room = Room.get(roomId)
        bbbCallService.closeRoom(room)

        redirect controller: 'room', action: 'index'
    }
    def leaveRoom(Room) { }
}

