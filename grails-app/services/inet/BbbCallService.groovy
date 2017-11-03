package inet

import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Value

import static groovyx.net.http.ContentType.*
import static java.net.URLEncoder.*

class BbbCallService {
    static final String API_BBBPATH = "/bigbluebutton/api/"
    static final String API_CREATE = "create"
    static final String API_INFO = "getMeetingInfo"
    static final String API_MEETINGS = "getMeetings"
    static final String API_JOIN = "join"
    static final String API_CHECKROOM = "isMeetingRunning"
    static final String API_ENDROOM = "end"


    @Value('${bbbconfig.shareSecret}')
    String shareSecret

    @Value('${bbbconfig.bbbserver}')
    String bbbserver

    @Value('${bbbconfig.demoserver}')
    String demoserver

    @Value('${bbbconfig.bbbport}')
    String bbbport

    @Value('${server.port}')
    String serverport

    def getBbbBaseUrl() {
        def rs = "http://${bbbserver}:${bbbport}"
        return rs
    }
    def getLogoutUrl() {
        def rs = "http://${demoserver}:${serverport}/login/logout"
        return rs
    }

    def createRoom(Room room) {
        def returncode
        def map
        def running = checkRoomRunning(room)

        if(running == false) {
            Map params = ["meetingID": room.id.toString(),
                          "name": room.name,
                          "welcome": room.welcome,
                          "moderatorPW": room.moderatorPW,
                          "attendeePW": room.attendeePW,
                        "logoutURL": getLogoutUrl()]
            map = prepareRequestParams(API_CREATE, params)

            def http = new RESTClient( map.uri )

            http.get( path: map.path, query: map.query, contentType: XML, headers : [Accept : 'application/xml'] ) { resp, xml ->
                returncode = xml.returncode
                log.debug("room code -create code: ${xml.returncode}")
                log.debug("room code -create message key: ${xml.messageKey}")
            }
        } else {
            return 'SUCCESS'
        }

        running = checkRoomRunning(room)
        room.attach()

        if(running == true) {
            room.running = true
        } else {
            room.running = false
        }

        room.save()
        return room.running ? 'SUCCESS' : 'FAILED'
    }

    def getRoomInfo(String meetingID) {
        Map params = ["meetingID": meetingID]
        prepareRequestParams(API_INFO, params)
    }

    def getRoomList() {
        Map params = [:]
        prepareRequestParams(API_MEETINGS, params)
    }

    def joinRoom(Room room, User user, String role = UtilService.ROLE_ATTENDEE) {

        //Create room blindly
        createRoom(room)

        //Check course to identify role attendee or teacher

        Map params = ["meetingID": room.id.toString(),
                      "fullName": user.fullName,
                      "userID": user.loginId,
                      "password": "${role == UtilService.ROLE_MODERATOR ? room.moderatorPW : room.attendeePW}"
        ]

        //Join user into the room
        def map = prepareRequestParams(API_JOIN, params)

        def sb = new StringBuffer("")
        map.query.eachWithIndex{  entry, int i ->
            if(i == 0) {
                sb.append("${entry.key}=${urlEncode(entry.value)}")
            } else {
                sb.append("&${entry.key}=${urlEncode(entry.value)}")
            }
        }
        def joinUrl = map.uri + map.path + "?" + sb.toString()
        log.debug("room code - joinurl: " + joinUrl)
        return joinUrl
    }

    def checkRoomRunning(Room room) {
        Map params = ["meetingID": room.id.toString()
        ]
        def map = prepareRequestParams(API_CHECKROOM, params)
        def http = new RESTClient( map.uri )

        def result = false

        http.get( path: map.path, query: map.query, contentType: XML, headers : [Accept : 'application/xml'] ) { resp, xml ->
            result = xml.running
            log.debug("room code -check code: ${xml.returncode}")
            log.debug("room code -check running: ${xml.running}")
        }

        return result
    }

    def closeRoom(Room room) {
        Map params = ["meetingID": room.id.toString(),
                      "password": room.moderatorPW
        ]
        def map = prepareRequestParams(API_ENDROOM, params)

        def http = new RESTClient( map.uri )

        http.get( path: map.path, query: map.query, contentType: XML, headers : [Accept : 'application/xml'] ) { resp, xml ->
            log.debug("room code -close code: ${xml.returncode}")
        }

        room.delete(flush: true)
    }
    def leaveRoom(Room) { }

    private def prepareRequestParams(String apiName, Map params) {

        def sb = new StringBuffer("")

        params.eachWithIndex{  entry, int i ->
            if(i == 0) {
                sb.append("${entry.key}=${urlEncode(entry.value)}")
            } else {
                sb.append("&${entry.key}=${urlEncode(entry.value)}")
            }
        }

        def checksum = getChecksum(apiName + sb.toString() + shareSecret)

        if (params.size() == 0){
            sb.append("checksum=${urlEncode(checksum)}")
        } else {
            sb.append("&checksum=${urlEncode(checksum)}")
        }

        return ['uri': getBbbBaseUrl(), 'path': API_BBBPATH + apiName, 'query': params + ['checksum': checksum]]
    }

    def urlEncode(String s) {
        try {
            return encode(s, "UTF-8")
        } catch (Exception e) {
            e.printStackTrace()
        }
        return ""
    }

    //
    // checksum() -- Return a checksum based on SHA-1 digest
    //
    def getChecksum(String s) {
        String checksum = ""
        try {
            checksum = org.apache.commons.codec.digest.DigestUtils.shaHex(s)
        } catch (Exception e) {
            e.printStackTrace()
        }
        return checksum
    }
}

//class BbbUtil {
//
////    static final String API_BBBPATH = "/bigbluebutton/api/"
////    static final String API_CREATE = "create"
////    static final String API_INFO = "getMeetingInfo"
////    static final String API_MEETINGS = "getMeetings"
////    static final String API_JOIN = "join"
////    static final String API_CHECKROOM = "isMeetingRunning"
////    static final String API_ENDROOM = "end"
//
//    @Value('${bbbconfig.bbbBaseUrl}')
//    static String bbbBaseUrl2
//
//    @Value('${bbbconfig.shareSecret}')
//    static String shareSecret2
//
//    //String apiName //create join end isMeetingRunning getMeetings getMeetingInfo
//    static def prepareRequestParams(String bbbBaseUrl, String shareSecret, String apiName, Map params) {
//
//        def sb = new StringBuffer("")
////        params.eachWithIndex{  entry, int p ->
////            System.out.println(entry.key + "=" + entry.value)
////        }
//        if(params.size() == 0){
//
//        } else {
//
//        }
//        params.eachWithIndex{  entry, int i ->
////                System.out.println(entry.key + "=" + entry.value)
//            if(i == 0) {
//                sb.append("${entry.key}=${urlEncode(entry.value)}")
//            } else {
//                sb.append("&${entry.key}=${urlEncode(entry.value)}")
//            }
//        }
////        sb.append("name=${urlEncode(params.name)}&meetingID=${urlEncode(params.meetingID)}&welcome=${urlEncode(params.welcome)}")
//
//        def checksum = getChecksum(apiName + sb.toString() + shareSecret)
//
//        if (params.size() == 0){
//            sb.append("checksum=${urlEncode(checksum)}")
//        } else {
//            sb.append("&checksum=${urlEncode(checksum)}")
//        }
//
//        def query = sb.toString()
//        sb.insert(0,bbbBaseUrl + apiName + "?")
//        System.out.println(sb.toString())
//        return ['uri': bbbBaseUrl, 'path': apiName, 'query': params + ['checksum': checksum]]
//    }
//
//    static def urlEncode(String s) {
//        try {
//            return encode(s, "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    //
//    // checksum() -- Return a checksum based on SHA-1 digest
//    //
//    static def getChecksum(String s) {
//        String checksum = "";
//        try {
//            checksum = org.apache.commons.codec.digest.DigestUtils.shaHex(s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return checksum;
//    }
//}
