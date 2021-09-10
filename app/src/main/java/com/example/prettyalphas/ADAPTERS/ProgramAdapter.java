package com.example.prettyalphas.ADAPTERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prettyalphas.R;

public class ProgramAdapter extends ArrayAdapter<String> {
    Context context;
    int[] images;
    String[] programName;
    String[] programDescription;
    public ProgramAdapter(Context context, String[] programName, int[] images, String[] programDescription) {
        super(context, R.layout.singlt_item,R.id.textView1,programName);
        this.context = context;
        this.images = images;
        this.programName = programName;
        this.programDescription = programDescription;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        View singleItem = convertView;
        ProgramViewHolder holder = null;
        if (singleItem == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.singlt_item,parent,false);
            holder = new ProgramViewHolder(singleItem);
            singleItem.setTag(holder);
        }
        else {
            holder = (ProgramViewHolder) singleItem.getTag();
        }
        holder.itemImage.setImageResource(images[position]);
        holder.programTitle.setText(programName[position]);
        holder.programDescription.setText(programDescription[position]);
        singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), programName[position], Toast.LENGTH_SHORT);
            }
        });
        return singleItem;
    }
    public class ProgramViewHolder {

        public ImageView itemImage;
        public TextView programTitle;
        public TextView programDescription;

        public ProgramViewHolder(View view){
            itemImage = view.findViewById(R.id.imageView);
            programTitle= view.findViewById(R.id.textView1);
            programDescription = view.findViewById(R.id.textView2);

        }
    }

}