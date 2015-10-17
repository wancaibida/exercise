package xplusz

import grails.transaction.Transactional

@Transactional
class HttpService {

    def serviceMethod() {

    }


    def req(String url, Map<String, String> params, String method = "post") {


        if (params) {
            def sb = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&")
            }
            sb.deleteCharAt(sb.length() - 1);
            url = url + "?" + sb.toString();
        }

        URL request = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) request.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "text/plain");
        connection.setRequestProperty("charset", "utf-8");
        connection.connect();


        StringBuilder result = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line)
        }
        rd.close();
        return result.toString();
    }
}
