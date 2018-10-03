package id.nindy.tugas3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    private List<SiswaModel> siswaModelsList;

    public RecyclerAdapter(List<SiswaModel> siswaModelsList) {
        this.siswaModelsList = siswaModelsList;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.siswa_item, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        holder.bind(holder, siswaModelsList.get(position));
    }

    @Override
    public int getItemCount() {
        return siswaModelsList.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_profile)
        ImageView imgProfile;

        @BindView(R.id.txt_name)
        TextView textName;

        @BindView(R.id.txt_address)
        TextView textAlamat;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final RecyclerHolder holder, final SiswaModel siswaModel){
            Glide.with(itemView).load(siswaModel.getPathFoto()).into(imgProfile);
            textName.setText(siswaModel.getNama());
            textAlamat.setText(siswaModel.getAlamat());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), ActionSiswaActivity.class);
                    intent.putExtra("DATA_SISWA", siswaModel);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }

    }

}
