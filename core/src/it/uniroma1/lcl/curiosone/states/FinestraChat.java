package it.uniroma1.lcl.curiosone.states;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import it.uniroma1.lcl.curiosone.Chat;

import static it.uniroma1.lcl.curiosone.Chat.HEIGHT;
import static it.uniroma1.lcl.curiosone.Chat.WIDTH;

public class FinestraChat extends State {
    //private Game game;
    private Stage stage;
	private ArrayList<Label> storico;
	private TextField barraChat;
    private TextButton send;
    private TextField inserimento;
    private Skin barra;
    private Texture goku;
    private Texture space;
    private Label messaggio;
    private boolean bot=true;
   // private final TextButton button;
    private int i;




	public FinestraChat(GameStateManager gsm,ArrayList<Label> storico) {
		super(gsm);
        goku=new Texture("gokuBlue.png");
        space=new Texture("Space.png");
		this.storico=storico;
		barra = new Skin(Gdx.files.internal("uiskin.json"));
       // this.game=game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        send=new TextButton("send",barra);
        send.setPosition(350,300);
        send.setSize(70,30);

        Gdx.input.setOnscreenKeyboardVisible(true);

        inserimento = new TextField("",barra);
        inserimento.setPosition(send.getX()-(inserimento.getWidth()*2)-25,send.getY());
        inserimento.setSize((WIDTH/2-send.getWidth())*2-25,30);
        stage.addActor(inserimento);

        messaggio = new Label("",barra);



        stage.addActor(send);




	}

	@Override
	public void handleInput() {

       send.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent e, float x, float y, int point,int button)
            {
                if(i==0)
                {
                    aggiornaPosizione();
                    messaggio.setText(inserimento.getText());
                    storico.add(messaggio);
                    messaggio.setPosition(utenteBot(inserimento.getText()), inserimento.getY() + messaggio.getHeight() + 50);

                    messaggio.setSize(50, 50);//fare in modo che sia in grado di contenere x caratteri per riga
                    bot = !bot;
                    inserimento.setText("");
                    i=1;
                }

            }
        });
	}

	@Override
	public void update(float dt) {
        i=0;
        if(send.isOver())
        handleInput();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(dt);
        stage.draw();
		
	}

	@Override
	public void render(SpriteBatch sb) {

		sb.begin();
        sb.draw(space,0,0,WIDTH,HEIGHT);
        sb.draw(goku,WIDTH/4,HEIGHT/4,WIDTH/2,HEIGHT/2);
        inserimento.draw(sb,1.0f);
        send.draw(sb,1.0f);
        messaggio.draw(sb,1.0f);
		sb.end();
		
	}

	@Override
	public void dispose()
    {
        goku.dispose();
        space.dispose();

	}

    public void aggiornaPosizione()
    {
        //spostare in alto la label in base alla dimensione del proprio messaggio ed a x pixel
        //dalla barra del messaggio e y pixel decisi per avere uno spazio tra messaggi
    }

    public int utenteBot(String s)
    {
        return bot? (10):((WIDTH-(s.length()*10))+10);
    }

}
