package Controle;

public class LoopPrincipal implements Runnable {
    public static final int DEFAULT_UPS = 80;
    public static final int DEFAULT_NO_DELAYS_PER_YIELD = 16;
    public static final int DEFAULT_MAX_FRAME_SKIPS = 5;
 
    private EtapasLoop game;
    private long desiredUpdateTime;
    private boolean running;
    private boolean CurrentLevel;
 
    private long afterTime;
    private long beforeTime = System.currentTimeMillis();
 
    private long overSleepTime = 0;
    private long excessTime = 0;
 
    private int noDelaysPerYield = DEFAULT_NO_DELAYS_PER_YIELD;
    private int maxFrameSkips = DEFAULT_MAX_FRAME_SKIPS;
    private int velocidade;
    private final int VEL_INICIAL = 60;
    
    int noDelays = 0;
    
    public LoopPrincipal(EtapasLoop loopSteps, int ups, int maxFrameSkips, int noDelaysPerYield) {
        super();
        if (ups < 1)
            throw new IllegalArgumentException("You must display at least one frame per second!");
 
        if (ups > 1000)
            ups = 1000;
 
        this.game = loopSteps;
        this.desiredUpdateTime = 1000000000L / ups;
        this.running = true;
        this.maxFrameSkips = maxFrameSkips;
        this.noDelaysPerYield = noDelaysPerYield;
        this.velocidade = VEL_INICIAL;
    }
 
    public LoopPrincipal(EtapasLoop loopSteps, int ups) {
        this(loopSteps, ups, DEFAULT_MAX_FRAME_SKIPS, DEFAULT_NO_DELAYS_PER_YIELD);
    }
 
    public LoopPrincipal(EtapasLoop loopSteps) {
        this(loopSteps, DEFAULT_UPS);
    }
 
    private void sleep(long nanos) {
        try {
            noDelays = 0;
            long beforeSleep = System.nanoTime();
            Thread.sleep(nanos / 1000000L, (int) (nanos % 1000000L));
            overSleepTime = System.nanoTime() - beforeSleep - nanos;
        } catch (Exception e) {}
    }
 
    private void yieldIfNeed() {
        if (++noDelays == noDelaysPerYield) {
            Thread.yield();
            noDelays = 0;
        }
    }
 
    private long calculateSleepTime() {
        return desiredUpdateTime - (afterTime - beforeTime) - overSleepTime;
    }
 
    public void run() {
        running = true;
 
        try {
        	while (running) {
	            game.setup(velocidade);
	            CurrentLevel = true;
	            while (CurrentLevel) {
	                beforeTime = System.nanoTime();
	                skipFramesInExcessTime();
	 
	                game.processLogics();
	                game.renderGraphics();
	                game.paintScreen();
	 
	                afterTime = System.nanoTime();
	                long sleepTime = calculateSleepTime();
	                if (sleepTime >= 0)
	                   sleep(sleepTime);
	                else {
	                   excessTime -= sleepTime; // Sleep time is negative
	                   overSleepTime = 0L;
	                   yieldIfNeed();
	                }
	            }
        	}
       } catch (Exception e) {
            e.printStackTrace();
       } finally {
           running = false;
           game.tearDown();
           System.exit(0);
       }
    }
 
    private void skipFramesInExcessTime() {
        int skips = 0;
        while ((excessTime > desiredUpdateTime) && (skips < maxFrameSkips)) {
            excessTime -= desiredUpdateTime;
            game.processLogics();
            skips++;
        }
    }
 
    public void stop() {
        running = false;
    }
    public void nextLevel() {
    	CurrentLevel = false;
    	velocidade += 30;
    }
}
