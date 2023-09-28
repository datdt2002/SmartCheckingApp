package vn.skymapglobal.smartcheckingapp.services;

import static io.realm.Realm.getApplicationContext;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginService {
    public static String login(String username, String password) {
        try {
            // URL của máy chủ đăng nhập
            URL url = new URL("https://armonitoring.skymapglobal.vn/api/authenticate");

            // Tạo kết nối HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // Tạo dữ liệu đăng nhập JSON
            String data = "{\"name\":\"" + username + "\",\"password\":\"" + password + "\"}";

            // Gửi dữ liệu đăng nhập
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            os.close();

            // Đọc phản hồi từ máy chủ
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}