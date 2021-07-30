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


        EventHandler<JosEvent> josEventHandler = new EventHandler<>();
        JosListenerImpl josListener = new JosListenerImpl(gl);

        EventHandler<AddHerstellerEvent> addHerstellerEventHandler = new EventHandler<>();
        AddHerstellerEventListnerImpl addHerstellerEventListener = new AddHerstellerEventListnerImpl(gl);

        EventHandler<ReceiveHerstellerListEvent> receiveHerstellerListEventHandler = new EventHandler<>();
        Listener<ReceiveHerstellerListEvent> receiveHerstellerListEventListener = new ReceiveHerstellerListEventListenerImpl(view);

        EventHandler<AddKuchenEvent> addKuchenEventHandler = new EventHandler<>();
        Listener<AddKuchenEvent> addKuchenEventListener = new AddKuchenEventListenerImpl(gl);

        EventHandler<ReceiveKuchenListEvent> receiveKuchenListEventHandler = new EventHandler<>();
        Listener<ReceiveKuchenListEvent> receiveKuchenListEventListener = new ReceiveKuchenListEventListnenerImpl(view);

        EventHandler<GetKuchenListEvent> getKuchenListEventHandler = new EventHandler<>();
        GetKuchenEventListenerImpl getKuchenListEventListener = new GetKuchenEventListenerImpl(gl);


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

