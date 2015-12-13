package hackpuc.vedor.utils;


import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import hackpuc.vedor.R;

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

    public static int inputBrandParty(String partyInitials){
        switch (partyInitials.toUpperCase()){
            case "DEM":
                return R.drawable.ic_dem;
            case "PC DO B":
                return R.drawable.ic_pcdob;
            case "PCB":
                return R.drawable.ic_pcb;
            case "PCO":
                return R.drawable.ic_pco;
            case "PDT":
                return R.drawable.ic_pdt;
            case "PEN":
                return R.drawable.ic_pen;
            case "PHS":
                return R.drawable.ic_phs;
            case "PMBD":
                return R.drawable.ic_pmdb;
            case "PMN":
                return R.drawable.ic_pmn;
            case "PP":
                return R.drawable.ic_pp;
            case "PPL":
                return R.drawable.ic_ppl;
            case "PPS":
                return R.drawable.ic_pps;
            case "PR":
                return R.drawable.ic_pr;
            case "PRB":
                return R.drawable.ic_prb;
            case "PROS":
                return R.drawable.ic_pros;
            case "PRP":
                return R.drawable.ic_prp;
            case "PRTB":
                return R.drawable.ic_prtb;
            case "PSB":
                return R.drawable.ic_psb;
            case "PSC":
                return R.drawable.ic_psc;
            case "PSD":
                return R.drawable.ic_psd;
            case "PSDB":
                return R.drawable.ic_psdb;
            case "PSDC":
                return R.drawable.ic_psdc;
            case "PSL":
                return R.drawable.ic_psl;
            case "PSOL":
                return R.drawable.ic_psol;
            case "PSTU":
                return R.drawable.ic_pstu;
            case "PT":
                return R.drawable.ic_pt;
            case "PT DO B":
                return R.drawable.ic_ptdob;
            case "PTB":
                return R.drawable.ic_ptb;
            case "PTC":
                return R.drawable.ic_ptc;
            case "PTN":
                return R.drawable.ic_ptn;
            case "PV":
                return R.drawable.ic_pv;
            case "SD":
                return R.drawable.ic_sd;
            default:
                return R.drawable.ic_pstu;
        }
    }

    public static String format(String value, String mask) {

        String data = new String();

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (Character.isDigit(c)) {
                data += c;
            }
        }

        int indMask = mask.length();
        int indField = data.length();

        for (; indField > 0 && indMask > 0; ) {
            if (mask.charAt(--indMask) == '#') {
                indField--;
            }
        }

        String exit = new String();
        for (; indMask < mask.length(); indMask++) {
            exit += ((mask.charAt(indMask) == '#') ? data.charAt(indField++) : mask.charAt(indMask));
        }
        return exit;
    }

    public static String formatCpf(String cpf) {
        while (cpf.length() < 11) {
            cpf = "0" + cpf;
        }
        return format(cpf, "###.###.###-##");
    }

    public static String formatCnpj(String cnpj) {
        while (cnpj.length() < 14) {
            cnpj = "0" + cnpj;
        }
        return format(cnpj, "##.###.###/####-##");
    }
}
