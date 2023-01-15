package com.example.application3;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RssAdapter extends RecyclerView.Adapter<RssAdapter.ViewHolder> {
    private final List<Item> items;

    public RssAdapter(List<Item> dataSet) {
        items = dataSet;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item,
                viewGroup, false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.txtTitle.setText(items.get(position).getTitle());
        holder.txtTitle.setClickable(true);
        holder.txtTitle.setMovementMethod(LinkMovementMethod.getInstance());
        String link = items.get(position).getLink();
        String title = items.get(position).getTitle();
        String text = String.format("<a href='%s'> %s </a>", link, title);
        holder.txtTitle.setText(Html.fromHtml(text));
        holder.descriptionView.setText(items.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtTitle;
        private final TextView descriptionView;

        public ViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txtTitle);
            descriptionView = view.findViewById(R.id.description);
        }
    }
}
