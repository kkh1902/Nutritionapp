//음식 추가 액티비티(AddFoodActivity) 리스트뷰 구현
package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListFood extends BaseAdapter{

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<ModelFood> foodData;

    public AdapterListFood(Context context, ArrayList<ModelFood> data) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        foodData = data;
    }

    @Override
    public int getCount() {
        return foodData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ModelFood getItem(int position) {
        return foodData.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.layout_list_food, null);

        TextView foodname = (TextView) view.findViewById(R.id.addfood_text_foodname);
        TextView foodattrib = (TextView) view.findViewById(R.id.addfood_text_foodattrib);

        foodname.setText(foodData.get(position).getFood_name());
        foodattrib.setText(foodData.get(position).getFood_once());

        return view;
    }
}
