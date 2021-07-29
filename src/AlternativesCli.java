import automat.GeschäftslogikImpl;
import automat.Beobachter;
import controller.*;
import view.*;

public class AlternativesCli {

    public static void main(String[] args) {

        int standart = 15;
        if(args.length > 0) {
            try {
                standart = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                return;
            }
        }
        GeschäftslogikImpl geschäftsLogic = new GeschäftslogikImpl(standart);
        GlWrapper gl = new GlWrapper(geschäftsLogic);
        ViewClass view = new ViewClass();
        Beobachter allergenBeobachter = new AllergenBeobachter(geschäftsLogic);


        JosEventHandler josEventHandler = new JosEventHandler();
        JosListenerImpl josListener = new JosListenerImpl(gl);

        AddHerstellerEventHandler addHerstellerEventHandler = new AddHerstellerEventHandler();
        AddHerstellerEventListnerImpl addHerstellerEventListener = new AddHerstellerEventListnerImpl(gl);

        ReceiveHerstellerListEventHandler receiveHerstellerListEventHandler = new ReceiveHerstellerListEventHandler();
        ReceiveHerstellerListEventListener receiveHerstellerListEventListener = new ReceiveHerstellerListEventListenerImpl(view);

        AddKuchenEventHandler addKuchenEventHandler = new AddKuchenEventHandler();
        AddKuchenEventListener addKuchenEventListener = new AddKuchenEventListenerImpl(gl);

        ReceiveKuchenListEventHandler receiveKuchenListEventHandler = new ReceiveKuchenListEventHandler();
        ReceiveKuchenListEventListener receiveKuchenListEventListener = new ReceiveKuchenListEventListnenerImpl(view);

        GetKuchenListEventHandler getKuchenListEventHandler = new GetKuchenListEventHandler();
        GetKuchenEventListenerImpl getKuchenListEventListener = new GetKuchenEventListenerImpl(gl);


        ReceiveAllergeneListener receiveAllergeneListener = new ReceiveAllergenListenerImpl(view);


        CliClass cli = new CliClass();

        cli.setHerstellerEventHandler(addHerstellerEventHandler);
        addHerstellerEventHandler.add(addHerstellerEventListener);

        cli.setKuchenEventHandler(addKuchenEventHandler);
        addKuchenEventHandler.add(addKuchenEventListener);

        cli.setGetKuchenListEventHandler(getKuchenListEventHandler);
        getKuchenListEventHandler.add(getKuchenListEventListener);

        cli.setJosEventHandler(josEventHandler);
        josEventHandler.add(josListener);

        addHerstellerEventListener.setHandler(receiveHerstellerListEventHandler);

        receiveHerstellerListEventHandler.add(receiveHerstellerListEventListener);

        getKuchenListEventListener.setReceiveKuchenListEventHandler(receiveKuchenListEventHandler);
        receiveKuchenListEventHandler.add(receiveKuchenListEventListener);

        cli.start();
    }
}

