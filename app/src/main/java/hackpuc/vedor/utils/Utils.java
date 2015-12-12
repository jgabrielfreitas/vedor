package hackpuc.vedor.utils;


import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public class Utils {

    public static void sendEmail(final String email, final String message) {
        new Thread(){
            public void run() {
                super.run();

                try {
                    OkHttpClient client = new OkHttpClient();

                    MediaType mediaType = MediaType.parse("multipart/form-data; boundary=---011000010111000001101001");
                    RequestBody body = RequestBody.create(mediaType, "-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"from\"\r\n\r\nMailgun Sandbox <postmaster@sandbox16820381aae74e2b8dcf0696fbb33a74.mailgun.org>\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"to\"\r\n\r\n" + email + "\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"subject\"\r\n\r\nHelloJoao\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"text\"\r\n\r\n" + message + "\r\n-----011000010111000001101001--");
                    Request request = new Request.Builder()
                                                 .url("https://api.mailgun.net/v3/sandbox16820381aae74e2b8dcf0696fbb33a74.mailgun.org/messages")
                                                 .post(body)
                                                 .addHeader("content-type", "multipart/form-data; boundary=---011000010111000001101001")
                                                 .addHeader("authorization", "Basic YXBpOmtleS0wOWJjNmJiMWIxMmMxN2QxNGFkNjcwNTVlOTkxOTQ4Ng==")
                                                 .addHeader("cache-control", "no-cache")
                                                 .addHeader("postman-token", "9b6ac8dc-a149-7aa0-9d1f-1f0e573e792f")
                                                 .build();

                    Response response = client.newCall(request).execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
