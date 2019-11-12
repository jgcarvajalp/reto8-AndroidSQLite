package co.edu.unal.reto8_androidsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import co.edu.unal.reto8_androidsqlite.model.Company;

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<Company> listasEmpresas = null;
    private ArrayList<Company> arrayList;

    public ListViewAdapter(Context mContext, List<Company> listasEmpresas) {
        this.mContext = mContext;
        this.listasEmpresas = listasEmpresas;
        this.inflater = LayoutInflater.from(this.mContext);
        this.arrayList = new ArrayList<Company>();
        this.arrayList.addAll(listasEmpresas);
    }

    public class ViewHolder{
        TextView name;
        TextView url;
        TextView phone;
        TextView email;
        TextView products;
        TextView classification;
    }

    @Override
    public int getCount() {
        return listasEmpresas.size();
    }

    @Override
    public Object getItem(int position) {
        return listasEmpresas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.companies_search_filter, null);
            // Buscar los datos y presentarlos en el listview_item.xml
            holder.name= (TextView) view.findViewById(R.id.filLayoutName);
            holder.url= (TextView) view.findViewById(R.id.filLayoutUrl);
            holder.phone= (TextView) view.findViewById(R.id.filLayoutPhone);
            holder.email= (TextView) view.findViewById(R.id.filLayoutEmail);
            holder.products= (TextView) view.findViewById(R.id.filLayoutProducts);
            holder.classification= (TextView) view.findViewById(R.id.filLayoutClass);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Establecer resultados en el TextView
        holder.name.setText(listasEmpresas.get(position).getName());
        holder.url.setText(listasEmpresas.get(position).getUrl());
        holder.phone.setText(listasEmpresas.get(position).getTelephone());
        holder.email.setText(listasEmpresas.get(position).getEmail());
        holder.products.setText(listasEmpresas.get(position).getProductsAndServices());
        holder.classification.setText(listasEmpresas.get(position).getCompanyClassification());
        return view;
    }

    // Función filtrar
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listasEmpresas.clear();
        if (charText.length() == 0) {
            listasEmpresas.addAll(arrayList);
        } else {
            for (Company wp : arrayList) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText) || wp.getCompanyClassification().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listasEmpresas.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
