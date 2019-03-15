package kr.co.naulsnow.androidsceneform;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.CameraConfig;
import com.google.ar.core.Config;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.UnavailableException;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import javax.sql.CommonDataSource;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "TAG_"+MainActivity.class.getSimpleName();

    ArFragment arFragment;
    private ModelRenderable renBear, renCat, renCow, renDog, renElephant, renFerret, renHippo, renHorse, renKoala, renLion, renReindeer, renWolverine;

    ImageView ivBear, ivCat, ivCow, ivDog, ivElephant, ivFerret, ivHippo, ivHorse, ivKoala, ivLion, ivReindeer, ivWolverine;

    View arrayView[];



    //데이터들
    int selected = 1; //Default Bear is selected
    boolean installRequested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //초기셋팅
        setUpModels();
        setArFragment();
        setViews();
        setListeners();
    }

    private void setUpModels(){

        ModelRenderable.builder()
                .setSource(this, R.raw.bear)
                .build().thenAccept(renderable -> renBear = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );

        ModelRenderable.builder()
                .setSource(this, R.raw.cat)
                .build().thenAccept(renderable -> renCat = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );

        ModelRenderable.builder()
                .setSource(this, R.raw.cow)
                .build().thenAccept(renderable -> renCow = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );

        ModelRenderable.builder()
                .setSource(this, R.raw.dog)
                .build().thenAccept(renderable -> renDog = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );

        ModelRenderable.builder()
                .setSource(this, R.raw.elephant)
                .build().thenAccept(renderable -> renElephant = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );

        ModelRenderable.builder()
                .setSource(this, R.raw.ferret)
                .build().thenAccept(renderable -> renFerret = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );

        ModelRenderable.builder()
                .setSource(this, R.raw.hippopotamus)
                .build().thenAccept(renderable -> renHippo = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );

        ModelRenderable.builder()
                .setSource(this, R.raw.horse)
                .build().thenAccept(renderable -> renHorse = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );

        ModelRenderable.builder()
                .setSource(this, R.raw.koala_bear)
                .build().thenAccept(renderable -> renKoala = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );

        ModelRenderable.builder()
                .setSource(this, R.raw.lion)
                .build().thenAccept(renderable -> renLion = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );

        ModelRenderable.builder()
                .setSource(this, R.raw.reindeer)
                .build().thenAccept(renderable -> renReindeer = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );

        ModelRenderable.builder()
                .setSource(this, R.raw.wolverine)
                .build().thenAccept(renderable -> renWolverine = renderable)
                .exceptionally(
                        throwable-> {
                            Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }


                );
    }


    private void setArFragment(){
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_ux_fragment);

        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                //When user tap on plane, we will add model
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());

                BaseTool.log(TAG, "plane 눌렸다. selected : "+selected);

                createModel(anchorNode, selected);
            }
        });




//        arFragment.getArSceneView().getSession().setCameraConfig(new CameraConfig(arFragment.getArSceneView().getSession(), ));


    }

    private void setViews(){
        ivBear = findViewById(R.id.iv_bear);
        ivCat = findViewById(R.id.iv_cat);
        ivCow = findViewById(R.id.iv_cow);
        ivDog = findViewById(R.id.iv_dog);
        ivElephant = findViewById(R.id.iv_elephant);
        ivFerret = findViewById(R.id.iv_ferret);
        ivHippo = findViewById(R.id.iv_hippo);
        ivHorse = findViewById(R.id.iv_horse);
        ivKoala = findViewById(R.id.iv_koala);
        ivLion = findViewById(R.id.iv_lion);
        ivReindeer = findViewById(R.id.iv_reindeer);
        ivWolverine = findViewById(R.id.iv_wolverine);

        arrayView = new View[]{
                ivBear, ivCat, ivCow, ivDog, ivElephant, ivFerret, ivHippo, ivHorse, ivKoala, ivLion, ivReindeer, ivWolverine
        };
    }

    private void setListeners() {

        for(int i=0;i<arrayView.length;i++) {
            int finalI = i;
            arrayView[i].setOnClickListener(v -> {
                selected = finalI+1;
                setBackground(arrayView[finalI].getId());

            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Session session = arFragment.getArSceneView().getSession();

        Log.d(TAG, "session 널이다 : "+(session == null));

        if(session == null){

            try{
                session = BaseTool.createArSession(this, installRequested);

                if(session == null ){
                    installRequested = CameraPermissionHelper.hasCameraPermission(this);
                    return;
                }else{
                    arFragment.getArSceneView().setupSession(session);
                }

            } catch (UnavailableException e) {
                BaseTool.handleSessionException(this, e);
            }

        }

        if(session!=null){
            Config config = session.getConfig();
            config.setFocusMode(Config.FocusMode.AUTO);
            config.setLightEstimationMode(Config.LightEstimationMode.AMBIENT_INTENSITY);
            config.setCloudAnchorMode(Config.CloudAnchorMode.ENABLED);
            arFragment.getArSceneView().getSession().configure(config);

        }

    }

    private void setBackground(int id){
        for(int i=0;i<arrayView.length;i++){
            if(arrayView[i].getId() == id)
                arrayView[i].setBackgroundColor(Color.parseColor("#aaffffff")); //set background for selected animal
            else
                arrayView[i].setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void createModel(AnchorNode anchorNode, int selected){
        String animalName = null, animalCost=null;

        TransformableNode animal = new TransformableNode(arFragment.getTransformationSystem());

        switch (selected){
            case 1:
                animal.setRenderable(renBear);
                animalName = "Bear";
                animalCost="$125,098";
                break;
            case 2:
                animal.setRenderable(renCat);
                animalName = "cat";
                animalCost="$350,908";
                break;
            case 3:
                animal.setRenderable(renCow);
                animalName = "cow";
                animalCost="$12,327";
                break;
            case 4:
                animal.setRenderable(renDog);
                animalName = "dog";
                animalCost="$7,250";
                break;
            case 5:
                animal.setRenderable(renElephant);
                animalName = "elephant";
                animalCost="$6,520,000";
                break;
            case 6:
                animal.setRenderable(renFerret);
                animalName = "ferret";
                animalCost="$3,256";
                break;
            case 7:
                animal.setRenderable(renHippo);
                animalName = "hippo";
                animalCost="$16,521";
                break;
            case 8:
                animal.setRenderable(renHorse);
                animalName = "horse";
                animalCost="$30,251";
                break;
            case 9:
                animal.setRenderable(renKoala);
                animalName = "koala";
                animalCost="$3,251";
                break;
            case 10:
                animal.setRenderable(renLion);
                animalName = "lion";
                animalCost="$963";
                break;
            case 11:
                animal.setRenderable(renReindeer);
                animalName = "reindeer";
                animalCost="$36,257";
                break;
            case 12:
                animal.setRenderable(renWolverine);
                animalName = "wolverine";
                animalCost="$36,257";
                break;
        }
        animal.getScaleController().setMaxScale(20.0f);
        animal.getScaleController().setMinScale(5.0f);
        animal.setLocalScale(new Vector3(10.0f, 10.0f, 10.0f));
        animal.setParent(anchorNode);
        animal.select();

        addName(anchorNode, animal, animalName, animalCost);

    }

    private void addName(AnchorNode anchorNode, TransformableNode model, String animalName, String animalCost) {

        //Each Model will have their own name;
        ViewRenderable.builder()
                .setView(this, R.layout.item_animal_name)
                .build()
                .thenAccept(renderable -> {
                    Node name = new Node();
                    name.setLocalScale(new Vector3(0.5f, 0.5f, 0.5f));
                    name.setLocalPosition(new Vector3(0f, model.getLocalPosition().y+0.3f, 0));
                    name.setParent(anchorNode);
                    name.setRenderable(renderable);

                    //setText
                    TextView tvAnimalName = renderable.getView().findViewById(R.id.tv_animal_name);
                    tvAnimalName.setText(animalName);

                    TextView tvAnimalCost = renderable.getView().findViewById(R.id.tv_animal_cost);
                    tvAnimalCost.setText(animalCost);

                    TextView tvCancel = renderable.getView().findViewById(R.id.tv_cancel);
                    tvCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            anchorNode.setParent(null);
                        }
                    });
                });



    }

}
