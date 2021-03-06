package prueba.prueba.crgomez.activitatdinamicfragment;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadoRecycler extends RecyclerView.Adapter<AdaptadoRecycler.elViewHolder> {

    public static ArrayList<CicleFlorida> llistatCicles;

    public static class elViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitol,tvDescripcio;
        ImageView icAnyadir,icDelete;
        Context context;
        public elViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescripcio = (TextView) itemView.findViewById(R.id.tvDescripcio);
            tvTitol = (TextView) itemView.findViewById(R.id.tv_titol);
            icAnyadir = (ImageView) itemView.findViewById(R.id.addRowBtn);
            icDelete = (ImageView) itemView.findViewById(R.id.delRowBtn);
            context = itemView.getContext();
        }
        //PONEMOS AQUÍ ESTE MÉTODO PARA PODER ACCEDER AL CONTEXTO DE LOS ACTIVITYS DESDE EL ADAPTADOR PORQUE ELVIEWHOLDER ES EL ÚNICO MÉTODO QUE PUEDE ACCEDER AL CONTEXTO
        //Aquí pondríamos todos los clicklistener que queremos porque con el context tenemos acceso, pero hay que llamar el método bajo para que vaya
        void setOnclickListeners(){

           icAnyadir.setOnClickListener(this);

        }
        // el BOTON DE AÑADIR.
        // con el switch decimos que botón estamos apretando.
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.addRowBtn:
                    Intent intent = new Intent(context,AgregarActivity.class);
                    intent.putExtra("prueba", tvTitol.getText());
                    //LE PASAMOS EL ARRAY Y LO AÑADIMOS, VEMOS EL LOG Y SE AÑADE OK.
                    intent.putParcelableArrayListExtra("arrayList", llistatCicles);
                    //Aquí no me funciona porque deberíamos devolver un ACTIVITY FOR RESULT PERO NO PUEDO... ENTONCES LE PASO UN STARTACTIVTY.
                    context.startActivity(intent);
                    break;
            }
        }
    }

    @NonNull
    @Override
    public elViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.un_element_de_de_la_llista,viewGroup,false);
        return new elViewHolder(v);
    }


    public AdaptadoRecycler(ArrayList<CicleFlorida> llista){

        llistatCicles = llista;
    }
    @Override
    public void onBindViewHolder(@NonNull elViewHolder elViewHolder, final int i) {

        elViewHolder.tvTitol.setText(llistatCicles.get(i).getTitol());
        elViewHolder.tvDescripcio.setText(llistatCicles.get(i).getDescripcio());
        // al hacer this estamos diciendo que está clase es la que va a ser quien implemente el onclick

        //El método este nos permite acceder al context.
        elViewHolder.setOnclickListeners();

        //AQUÍ AÑADIMOS EL IC_DELETE CON SU LISTENER, "I" INDICAMOS LA POSICIÓN DEL ARRAY.
        elViewHolder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                llistatCicles.remove(i);
                notifyItemRemoved(i);



            }
        });

    }



    @Override
    public int getItemCount() {
        return llistatCicles.size();
    }


}
