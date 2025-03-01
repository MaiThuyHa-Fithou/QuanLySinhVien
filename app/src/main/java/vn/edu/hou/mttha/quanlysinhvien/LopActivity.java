package vn.edu.hou.mttha.quanlysinhvien;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import vn.edu.hou.mttha.quanlysinhvien.DB.LopDAO;

public class LopActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd, btnDsLop, btnDong;
    EditText etTenLop;
    ListView lvLop;
    //khai bao cac thuoc tinh xu ly database
    LopDAO lopDAO;//thuc hien voi csdl tbl_Lop
    ArrayList<String> dsLop;
    ArrayAdapter<String> lopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lop);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getViews();
        //khoi tao cac doi tuong lien quan
        lopDAO = new LopDAO(LopActivity.this);
        dsLop = new ArrayList<>();

    }

    private void getViews(){
        btnAdd= findViewById(R.id.btnAdd);
        btnDsLop = findViewById(R.id.btnDsLop);
        btnDong = findViewById(R.id.btnDong);
        etTenLop = findViewById(R.id.etTenLop);
        lvLop = findViewById(R.id.lvLop);
        //gan xu ly su kien cho cac button
        btnDong.setOnClickListener(this::onClick);
        btnAdd.setOnClickListener(this::onClick);
        btnDsLop.setOnClickListener(this::onClick);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAdd){
            //them moi ten lop vao trong bang tbl_lop
            String tenLop = etTenLop.getText().toString();
            lopDAO.themLop(tenLop);
            etTenLop.setText("");
        }
        if(view.getId()==R.id.btnDsLop){
            //doc du lieu tu bang tbl_lop va hien thi len listview
            dsLop = lopDAO.getAllLop();
            lopAdapter = new ArrayAdapter<>(LopActivity.this,
                    android.R.layout.simple_list_item_1,dsLop);
            //set adapter vao listview
            lvLop.setAdapter(lopAdapter);
        }
        if(view.getId()==R.id.btnDong){
            finish();
        }
    }
}