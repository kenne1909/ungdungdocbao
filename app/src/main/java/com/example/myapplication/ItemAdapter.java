package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Item> itemList;

    private String formatPubDate(String pubDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
            inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

            Date date = inputFormat.parse(pubDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE, d/MM/yyyy, HH:mm", Locale.US);
            outputFormat.setTimeZone(TimeZone.getTimeZone("GMT+7")); // Điều chỉnh múi giờ theo giờ Việt Nam

            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return pubDate; // Trả về giá trị gốc nếu có lỗi
        }
    }

    public ItemAdapter(Context context, int layout, List<Item> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    private class ViewHolder{
        TextView txtten,textdate;

        ImageView img;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            holder = new ViewHolder();

            //ansh xa
            holder.txtten=view.findViewById(R.id.textviewTen);
            holder.textdate=view.findViewById(R.id.textviewdate);
            holder.img=view.findViewById(R.id.imageView);

            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }

        //gasn gia tri

        Item item=itemList.get(i);
        holder.txtten.setText(item.getTen());
        String formattedDate = formatPubDate(item.getDate());
        holder.textdate.setText(formattedDate);
        Picasso.get().load(item.getImage()).into(holder.img);

        return view;
    }
}
