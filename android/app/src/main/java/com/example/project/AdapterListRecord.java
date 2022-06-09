//음식 추가 액티비티(AddFoodActivity) 리스트뷰 구현
package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListRecord extends BaseAdapter{

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<ModelRecord> recordData;

    public AdapterListRecord(Context context, ArrayList<ModelRecord> data) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        recordData = data;
    }

    @Override
    public int getCount() {
        return recordData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ModelRecord getItem(int position) {
        return recordData.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.layout_list_food, null);

        TextView foodname = (TextView) view.findViewById(R.id.addfood_text_foodname);
        TextView foodattrib = (TextView) view.findViewById(R.id.addfood_text_foodattrib);

        foodname.setText(recordData.get(position).getFood());
        foodattrib.setText(recordData.get(position).getMeal_time());

        return view;
    }
}
