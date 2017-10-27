package inet

class RoomController {

    def bbbCallService

    def index() {
        def roomList = Room.list()
        def roomCount = Room.count()
        return [roomList: roomList, roomCount: roomCount]
    }

    def create() {
        def newRoom = new Room()
        return [room: newRoom]
    }

    def save(Room newRoom) {
        if (newRoom.validate()) {

            newRoom.save()
            bbbCallService.createRoom(newRoom)
            flash.message = "Successfully Created room"
            redirect(uri: '/room/index')
        } else {
            flash.message = "Error create room"
            return [ room: newRoom ]
        }
    }

}
