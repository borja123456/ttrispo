package com.mygdx.ttrispo.BaseDeDatos;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

import de.tomgrill.gdxfirebase.core.FirebaseConfiguration;
import de.tomgrill.gdxfirebase.core.FirebaseFeatures;
import de.tomgrill.gdxfirebase.core.FirebaseLoader;
import de.tomgrill.gdxfirebase.core.GDXFirebase;
import de.tomgrill.gdxfirebase.core.database.DataSnapshot;
import de.tomgrill.gdxfirebase.core.database.DatabaseError;
import de.tomgrill.gdxfirebase.core.database.DatabaseReference;
import de.tomgrill.gdxfirebase.core.database.ValueEventListener;

public class FirebaseHelper {

    private FirebaseConfiguration config;
    private DatabaseReference databaseReference;
    private final ArrayList<Jugador> listaRanking = new ArrayList<>();

    public FirebaseHelper(){
        //configurar base de datos
        config = new FirebaseConfiguration();
        config.databaseUrl = "https://ttrispo-40d29.firebaseio.com";
        config.serviceAccount = Gdx.files.absolute("ttrispo-40d29-firebase-adminsdk-duoa1-62d89da538.json");

        try{
            //cargar base de datos en tiempo real
            FirebaseLoader.load(config,
                    FirebaseFeatures.REALTIME_DATABASE,
                    FirebaseFeatures.AUTHENTICATION
            );
        }catch (RuntimeException re){
            System.out.println("Error, ya esta creado y conectad la base de datos.");
        }

    }

    public void insertarPuntuacionEnRanking(String nombre, long puntos) {
        int contador = 1;
        boolean esMayorQueAlguno = false;
        int posicionJugadorNuevo = 1;
        while(!esMayorQueAlguno && contador<listaRanking.size()){
            if(listaRanking.get(contador).getPuntuacion()<=puntos){
                Jugador jugador = new Jugador(nombre, puntos);
                listaRanking.add(jugador);
                reordenarArray();
                listaRanking.remove(11);
                if(nombre!=null) {
                    for (int i = 1; i < listaRanking.size(); i++) {
                        if (puntos == listaRanking.get(i).getPuntuacion()) {
                            break;
                        } else posicionJugadorNuevo++;
                    }
                }else{
                    nombre="annonymous";
                }
                listaRanking.get(posicionJugadorNuevo).setNombre(nombre);
                esMayorQueAlguno = true;
            }
            contador++;
        }
        if (esMayorQueAlguno){
            databaseReference = GDXFirebase.FirebaseDatabase().getReference("bbdd");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int i = 1;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String clave = snapshot.getKey();
                        if (Integer.parseInt(clave) != 0) {
                            databaseReference.child(String.valueOf(i)).child("Nombre").setValue(listaRanking.get(i).getNombre());
                            databaseReference.child(String.valueOf(i)).child("Puntuacion").setValue(listaRanking.get(i).getPuntuacion());
                            i++;
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }
    /** rellena el array del ranking, maximo 10
     **/
    public void rellenarArrayDeRanking(final FirebaseCallback firebaseCallback){
        DatabaseReference dr = GDXFirebase.FirebaseDatabase().getReference("bbdd");

        dr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int i = 1;
                    listaRanking.clear();
                    listaRanking.add(null);//En la posicion 0, porque no me interesa
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String clave = snapshot.getKey();
                        if (Integer.parseInt(clave) != 0) { // El 0 esta reservado para que se utilize como contador, lo he puesto solo por si acaso, y por las pruebas que hice
                            Jugador jugador = new Jugador(snapshot.child("Nombre").getValue().toString(), (long) snapshot.child("Puntuacion").getValue());
                            listaRanking.add(jugador);
                            if (i == 10) {
                                break;
                            }
                            i++;
                        }
                    }
                }
                firebaseCallback.onCallback(listaRanking);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    /** LO QUE HAY QUE HACER ES EL METODO DE BURBUJA para reordenar el array de mayor a menor(esto es asi porque quiero y puedo :D es temporalmente)
     */
    private void reordenarArray(){
        int i, j;
        String auxNombre;
        long auxPuntuacion;

        for (i = 0; i <= 11 - 1; i++) {
            for (j = 1; j <= 11 - i - 1; j++) {
                if (listaRanking.get(j+1).getPuntuacion() > listaRanking.get(j).getPuntuacion()) {
                    auxNombre = listaRanking.get(j+1).getNombre();
                    auxPuntuacion = listaRanking.get(j+1).getPuntuacion();

                    listaRanking.get(j+1).setNombre(listaRanking.get(j).getNombre());
                    listaRanking.get(j+1).setPuntuacion(listaRanking.get(j).getPuntuacion());

                    listaRanking.get(j).setNombre(auxNombre);
                    listaRanking.get(j).setPuntuacion(auxPuntuacion);
                }
            }
        }
    }
}