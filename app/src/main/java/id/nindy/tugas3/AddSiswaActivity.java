package id.nindy.tugas3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSiswaActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText editName;

    @BindView(R.id.et_address)
    EditText editAddress;

    @BindView(R.id.ib_camera)
    ImageButton imageButton;

    @BindView(R.id.btnAdd)
    Button btnAdd;

    private SiswaModel siswaModel;
    private ArrayList<Image> imageLibrary = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_siswa);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ib_camera)
    public void ImageBtnClick(View v){
        ImagePicker.with(this)
                .setFolderMode(true)
                .setMaxSize(10)
                .setMultipleMode(false)
                .setCameraOnly(false)
                .setFolderTitle("SMKCoding")
                .setSelectedImages(imageLibrary)
                .setAlwaysShowDoneButton(true)
                .setKeepScreenOn(true)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){
            imageLibrary = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            Glide.with(this).load(imageLibrary.get(0).getPath()).into(imageButton);
        }
    }

    @OnClick(R.id.btnAdd)
    public void BtnAddClick(View v){
        if(!editName.getText().toString().isEmpty() && !editAddress.getText().toString().isEmpty() && !imageLibrary.isEmpty()){
            siswaModel = new SiswaModel();
            siswaModel.setNama(editName.getText().toString());
            siswaModel.setAlamat((editAddress.getText().toString()));
            siswaModel.setPathFoto(imageLibrary.get(0).getPath());

            SiswaApp.db.userDao().insertAll(siswaModel);

            Intent intent = new Intent(AddSiswaActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
