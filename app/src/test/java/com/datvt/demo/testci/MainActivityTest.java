package com.datvt.demo.testci;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.junit.*;
import org.junit.runner.*;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    private MainActivity activity;

    private EditText valueX, valueY;

    private TextView result;

    private Button addButton;

    /**
     * - Bắt đầu 1 test case sử dụng hàm setUp (@Before), ở đây mình đang thực hiện viết test case cho
     * RobolectriActivity.
     */
    @Before
    public void setUp() throws Exception {
        // khơi tạo activity
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
        // khởi tạo các thành phần sử dụng trong activity
        valueX = activity.findViewById(R.id.edt_x);
        valueY = activity.findViewById(R.id.edt_y);
        result = activity.findViewById(R.id.tv_sum);
        addButton = activity.findViewById(R.id.btn_ok);
    }

    /**
     * - Bắt đầu mỗi hàm test thêm ký tự (@Test)
     */
    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(activity);
        assertNotNull(valueX);
        assertNotNull(valueY);
        assertNotNull(result);
        assertNotNull(addButton);
    }

    // test case kiểm tra giá trị nhập vào là 1 số nguyên
    @Test
    public void testInputValue() throws Exception {
        assertThat(TextUtils.isDigitsOnly(valueX.getText()), equalTo(true));
        assertThat(TextUtils.isDigitsOnly(valueY.getText()), equalTo(true));
    }

    //test case kiểm tra kết quả là chính xác khi nhập (8 + 1  = 9)
    @Test
    public void testResult() throws Exception {
        valueX.setText("8");
        valueY.setText("2");
        addButton.performClick();
        assertThat(result.getText().toString(), equalTo("9"));
    }
}
