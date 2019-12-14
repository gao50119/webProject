package cn.itcast.itcaststore.utils;

import java.util.UUID;

public class IdUtils {

	public static String getUUID() {
		UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1];
	}
}
