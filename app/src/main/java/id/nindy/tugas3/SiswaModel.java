package id.nindy.tugas3;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class SiswaModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String nama;

    @ColumnInfo(name = "alamat")
    private String alamat;

    @ColumnInfo(name = "path_foto")
    private String pathFoto;

    public SiswaModel() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    protected SiswaModel(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        alamat = in.readString();
        pathFoto = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeString(pathFoto);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SiswaModel> CREATOR = new Creator<SiswaModel>() {
        @Override
        public SiswaModel createFromParcel(Parcel in) {
            return new SiswaModel(in);
        }

        @Override
        public SiswaModel[] newArray(int size) {
            return new SiswaModel[size];
        }
    };
}
