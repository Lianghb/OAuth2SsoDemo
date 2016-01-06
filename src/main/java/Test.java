import java.util.Arrays;
import java.util.Base64;

/**
 * Created by boxfish on 16/1/6.
 */
public class Test {
    public static void main(String[] args) {
        String s = "{\"typ\":\"JWT\",\n" +
                "      \"alg\":\"HS256\"}";
        byte[] bytes = s.getBytes();
        Base64.Encoder urlEncoder = Base64.getUrlEncoder();
        Base64.Decoder urlDecoder =Base64.getUrlDecoder();
        System.out.println("byte：" + Arrays.toString(bytes));
//        String es = Base64Utils.encodeToString(bytes);
//        System.out.println("加密后：" + es);
//        System.out.println("解码后：" + new String(Base64Utils.decodeFromString(es)));

        String es = urlEncoder.encodeToString(bytes);
        System.out.println("2加密后：" + es);
        System.out.println("2解密后：" + new String(urlDecoder.decode(es)));


    }
}
//eyJ0eXAiOiJKV1QiLA0KICJhbGciOiJIUzI1NiJ9
//eyJ0eXAiOiJKV1QiLAogICAgICAiYWxnIjoiSFMyNTYifQ==
//eyJ0eXAiOiJKV1QiLAogICAgICAiYWxnIjoiSFMyNTYifQ==