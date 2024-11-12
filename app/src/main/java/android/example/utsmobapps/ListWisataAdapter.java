package android.example.utsmobapps;

import android.content.Context;
import android.content.Intent;
import android.example.utsmobapps.DetailWisata.DetailWisataJawaTengahActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

public class ListWisataAdapter extends RecyclerView.Adapter<ListWisataAdapter.ListViewHolder> {

    private ArrayList<Wisata> listWisata;

    public ListWisataAdapter(ArrayList<Wisata> listWisata) {
        this.listWisata = listWisata;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_wisata, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Wisata wisata = listWisata.get(position);

        Glide.with(holder.itemView.getContext())
                .load(wisata.getPhoto())
                .apply(new RequestOptions().placeholder(R.drawable.profile))
                .into(holder.imgPhoto);

        holder.tvName.setText(wisata.getName());
        holder.tvLocation.setText(wisata.getLocation());

        Context mContext = holder.itemView.getContext();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveDetail = new Intent(mContext, DetailWisataJawaTengahActivity.class);
                moveDetail.putExtra(DetailWisataJawaTengahActivity.EXTRA_LOCATION, wisata.getLocation());
                moveDetail.putExtra(DetailWisataJawaTengahActivity.EXTRA_NAME, wisata.getName());
                moveDetail.putExtra(DetailWisataJawaTengahActivity.EXTRA_PHOTO, wisata.getPhoto());
                moveDetail.putExtra(DetailWisataJawaTengahActivity.EXTRA_HIGHLIGHTS, wisata.getHighlights());
                moveDetail.putExtra(DetailWisataJawaTengahActivity.EXTRA_DESCRIPTION, wisata.getDescription());
                mContext.startActivity(moveDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listWisata.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation;
        ImageView imgPhoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvLocation = itemView.findViewById(R.id.tv_item_location);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}


