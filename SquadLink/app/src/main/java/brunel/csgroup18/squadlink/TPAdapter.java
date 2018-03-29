package brunel.csgroup18.squadlink;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nathan Hoy on 29/03/2018.
 */

public class TPAdapter extends RecyclerView.Adapter<TPAdapter.ViewHolderTrainingPlans> {

    private LayoutInflater layoutInflater;

    public TPAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolderTrainingPlans onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.coach_tp_list, parent, false);
        ViewHolderTrainingPlans viewHolder = new ViewHolderTrainingPlans(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderTrainingPlans holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolderTrainingPlans extends RecyclerView.ViewHolder{

        private TextView tpTitle;

        public ViewHolderTrainingPlans(View itemView){
            super(itemView);

            tpTitle = (TextView) itemView.findViewById(R.id.tp_title);

        }

    }


}
