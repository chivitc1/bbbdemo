package inet

import org.apache.commons.lang.RandomStringUtils


class UtilService {

    static def randomPassword() {
        String charset = (('A'..'Z') + ('0'..'9') +('!#.,{[]}')).join()
        Integer length = 9
        String randomString = RandomStringUtils.random(length, charset.toCharArray())
        return randomString
    }

    static String ROLE_MODERATOR = "ROLE_MODERATOR"
    static String ROLE_ATTENDEE = "ROLE_ATTENDEE"
}
