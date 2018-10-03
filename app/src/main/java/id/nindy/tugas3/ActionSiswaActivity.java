package id.nindy.tugas3;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActionSiswaActivity extends AppCompatActivity {

    @BindView(R.id.et_action_name)
    EditText editName;
    @BindView(R.id.et_action_address) EditText editAddress;
    @BindView(R.id.iv_action_profile)
    ImageView imageView;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_delete) Button btnDelete;

    private SiswaModel siswaModel;
    private ArrayList<Image> imageArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_siswa);
        ButterKnife.bind(this);

        siswaModel = getIntent().getParcelableExtra("DATA_SISWA");

        editName.setText(siswaModel.getNama());
        editAddress.setText(siswaModel.getAlamat());

        Glide.with(this)
                .load(siswaModel.getPathFoto())
                .into(imageView);
    }

    @OnClick(R.id.iv_action_profile)
    public void ImageBtnClick(View v){
        ImagePicker.with(this)
                .setFolderMode(true)
                .setMaxSize(10)
                .setMultipleMode(false)
                .setCameraOnly(false)
                .setFolderTitle("SMKCoding")
                .setSelectedImages(imageArrayList)
                .setAlwaysShowDoneButton(true)
                .setKeepScreenOn(true)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            imageArrayList = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            siswaModel.setPathFoto(imageArrayList.get(0).getPath());
            Glide.with(this).load(imageArrayList.get(0).getPath()).into(imageView);
        }
    }

    @OnClick(R.id.btn_update)
    public void onBtnUpdateClick() {
        siswaModel.setNama(editName.getText().toString());
        siswaModel.setAlamat(editAddress.getText().toString());
        siswaModel.setPathFoto(siswaModel.getPathFoto());

        SiswaApp.db.userDao().update(siswaModel);

        Intent intent = new Intent(new Intent(this, MainActivity.class));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @OnClick(R.id.btn_delete)
    public void onBtnDeleteClick() {
        SiswaApp.db.userDao().deleteUsers(siswaModel);
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }


}

