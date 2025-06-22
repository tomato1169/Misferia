package azerot.azerot.command;

import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.effect.*;
import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class spawn extends AbstractCommand {
    public spawn() {
        super("spawn");
    }

    public void execute(CommandSender sender, String label, String[] args) throws SQLException {
        Player p = (Player) sender;
        Location loc = p.getLocation();

        int particls = Integer.parseInt(args[0]);

        PacketPlayOutWorldParticles particles = null;
        final int[] i = {0};
        switch (particls){
            case 1:
                VortexEffect starEffect = new VortexEffect(azerot.getEffectManager());
                starEffect.setLocation(loc.add(0,1,0));
                starEffect.setTargetPlayer(p);
                starEffect.particle = Particle.REDSTONE;
                starEffect.iterations = 2;
                starEffect.duration = 20;
                starEffect.start();
                break;
            case 2:
                AnimatedBallEffect starEffect1 = new AnimatedBallEffect(azerot.getEffectManager());
                starEffect1.setLocation(loc.add(0,1,0));
                starEffect1.setTargetPlayer(p);
                starEffect1.particle = Particle.REDSTONE;

                starEffect1.iterations = 2;
                starEffect1.duration = 20;
                starEffect1.start();
                break;
            case 3:
                ArcEffect starEffect2 = new ArcEffect(azerot.getEffectManager());
                starEffect2.setLocation(loc.add(0,1,0));
                starEffect2.setTargetPlayer(p);
                starEffect2.particle = Particle.REDSTONE;
                starEffect2.iterations = 2;
                starEffect2.duration = 20;
                starEffect2.start();
                break;
            case 4:
                AtomEffect starEffect3 = new AtomEffect(azerot.getEffectManager());
                starEffect3.setLocation(loc.add(0,1,0));
                starEffect3.setTargetPlayer(p);
                starEffect3.particleNucleus = Particle.REDSTONE;
                starEffect3.particleOrbital = Particle.REDSTONE;
                starEffect3.angularVelocity = 2;
                starEffect3.colorNucleus = Color.AQUA;
                starEffect3.colorOrbital = Color.ORANGE;
                starEffect3.particleCount = 150;
                starEffect3.radius = 3;
                starEffect3.iterations = 2;
                starEffect3.duration = 200;
                starEffect3.start();
                break;
            case 5:
                BigBangEffect starEffect4 = new BigBangEffect(azerot.getEffectManager());
                starEffect4.setLocation(loc.add(0,1,0));
                starEffect4.targetPlayer = p;
                starEffect4.type = EffectType.DELAYED;
                starEffect4.fireworkType = FireworkEffect.Type.STAR;
                starEffect4.iterations = 1;
                starEffect4.duration = 200;
                starEffect4.start();
                break;
            case 6:
                BleedEffect starEffect5 = new BleedEffect(azerot.getEffectManager());
                starEffect5.setLocation(loc.add(0,1,0));
                starEffect5.setTargetPlayer(p);
                starEffect5.type = EffectType.INSTANT;
                starEffect5.iterations = 2;
                starEffect5.duration = 20;
                starEffect5.start();
                break;
            case 7:
                CircleEffect starEffect6 = new CircleEffect(azerot.getEffectManager());
                starEffect6.setLocation(loc.add(0,1,0));
                starEffect6.setTargetPlayer(p);
                starEffect6.particle = Particle.REDSTONE;
                starEffect6.radius = 3;
                starEffect6.maxAngle = 20;
                starEffect6.iterations = 2;
                starEffect6.duration = 200;
                starEffect6.start();
                break;
            case 8:
                CloudEffect starEffect7 = new CloudEffect(azerot.getEffectManager());
                starEffect7.setLocation(loc.add(0,1,0));
                starEffect7.setTargetPlayer(p);
                starEffect7.mainParticle = Particle.REDSTONE;
                starEffect7.cloudParticle = Particle.REDSTONE;
                starEffect7.color = Color.WHITE;
                starEffect7.cloudColor = Color.BLACK;
                starEffect7.particleRadius = 2;
                starEffect7.iterations = 2;
                starEffect7.duration = 20;
                starEffect7.start();
                break;
            case 9:
                ColoredImageEffect starEffect8 = new ColoredImageEffect(azerot.getEffectManager());
                starEffect8.setLocation(loc.add(0,1,0));
                starEffect8.setTargetPlayer(p);
                starEffect8.particle = Particle.REDSTONE;
                starEffect8.iterations = 2;
                starEffect8.duration = 20;
                starEffect8.start();
                break;
            case 10:
                ConeEffect starEffect9 = new ConeEffect(azerot.getEffectManager());
                starEffect9.setLocation(loc.add(0,1,0));
                starEffect9.setTargetPlayer(p);
                starEffect9.particle = Particle.REDSTONE;
                starEffect9.iterations = 2;
                starEffect9.duration = 20;
                starEffect9.start();
                break;
            case 11:
                CubeEffect starEffect10 = new CubeEffect(azerot.getEffectManager());
                starEffect10.setLocation(loc.add(0,1,0));
                starEffect10.setTargetPlayer(p);
                starEffect10.particle = Particle.REDSTONE;
                starEffect10.iterations = 2;
                starEffect10.duration = 20;
                starEffect10.start();
                break;
            case 12:
                CuboidEffect starEffect11 = new CuboidEffect(azerot.getEffectManager());
                starEffect11.setLocation(loc.add(0,1,0));
                starEffect11.setTargetPlayer(p);
                starEffect11.particle = Particle.REDSTONE;
                starEffect11.iterations = 2;
                starEffect11.duration = 20;
                starEffect11.start();
                break;
            case 13:
                CylinderEffect starEffect12 = new CylinderEffect(azerot.getEffectManager());
                starEffect12.setLocation(loc.add(0,1,0));
                starEffect12.setTargetPlayer(p);
                starEffect12.particle = Particle.REDSTONE;
                starEffect12.iterations = 2;
                starEffect12.duration = 20;
                starEffect12.start();
                break;
            case 14:
                DiscoBallEffect starEffect13 = new DiscoBallEffect(azerot.getEffectManager());
                starEffect13.setLocation(loc.add(0,1,0));
                starEffect13.setTargetPlayer(p);
                starEffect13.sphereParticle = Particle.REDSTONE;
                starEffect13.lineParticle = Particle.REDSTONE;
                starEffect13.iterations = 2;
                starEffect13.duration = 20;
                starEffect13.start();
                break;
            case 15:
                DnaEffect starEffect14 = new DnaEffect(azerot.getEffectManager());
                starEffect14.setLocation(loc.add(0,1,0));
                starEffect14.setTargetPlayer(p);
                starEffect14.particleBase1 = Particle.REDSTONE;
                starEffect14.particleBase2 = Particle.REDSTONE;
                starEffect14.particleHelix = Particle.REDSTONE;
                starEffect14.iterations = 2;
                starEffect14.duration = 20;
                starEffect14.start();
                break;
            case 16:
                DonutEffect starEffect15 = new DonutEffect(azerot.getEffectManager());
                starEffect15.setLocation(loc.add(0,1,0));
                starEffect15.setTargetPlayer(p);
                starEffect15.particle = Particle.REDSTONE;
                starEffect15.iterations = 2;
                starEffect15.duration = 20;
                starEffect15.start();
                break;
            case 17:
                DragonEffect starEffect16 = new DragonEffect(azerot.getEffectManager());
                starEffect16.setLocation(loc.add(0,1,0));
                starEffect16.setTargetPlayer(p);
                starEffect16.particle = Particle.REDSTONE;
                starEffect16.particles = 50;
                starEffect16.iterations = 2;
                starEffect16.duration = 200;
                starEffect16.start();
                break;
            case 18:
               EarthEffect starEffect17 = new EarthEffect(azerot.getEffectManager());
                starEffect17.setLocation(loc.add(0,1,0));
                starEffect17.setTargetPlayer(p);
                starEffect17.particleLand = Particle.REDSTONE;
                starEffect17.particleOcean = Particle.REDSTONE;
                starEffect17.colorLand = Color.GREEN;
                starEffect17.colorOcean = Color.AQUA;
                starEffect17.iterations = 1;
                starEffect17.duration = 200;
                starEffect17.start();
                break;
            case 19:
                EquationEffect starEffect18 = new EquationEffect(azerot.getEffectManager());
                starEffect18.setLocation(loc.add(0,1,0));
                starEffect18.setTargetPlayer(p);
                starEffect18.particle = Particle.REDSTONE;
                starEffect18.iterations = 2;
                starEffect18.duration = 20;
                starEffect18.start();
                break;
            case 20:
                ExplodeEffect starEffect21 = new ExplodeEffect(azerot.getEffectManager());
                starEffect21.setLocation(loc.add(0,1,0));
                starEffect21.setTargetPlayer(p);
                starEffect21.particle1 = Particle.REDSTONE;
                starEffect21.particle2 = Particle.REDSTONE;
                starEffect21.iterations = 2;
                starEffect21.duration = 20;
                starEffect21.start();
                break;
            case 21:
                FountainEffect starEffect22 = new FountainEffect(azerot.getEffectManager());
                starEffect22.setLocation(loc.add(0,1,0));
                starEffect22.setTargetPlayer(p);
                starEffect22.particle = Particle.REDSTONE;
                starEffect22.color = Color.OLIVE;
                starEffect22.iterations = 1;
                starEffect22.duration = 20;
                starEffect22.start();
                break;
            case 22:
                GridEffect starEffect23 = new GridEffect(azerot.getEffectManager());
                starEffect23.setLocation(loc.add(0,1,0));
                starEffect23.setTargetPlayer(p);
                starEffect23.particle = Particle.REDSTONE;
                starEffect23.iterations = 2;
                starEffect23.duration = 20;
                starEffect23.start();
                break;
            case 23:
                HeartEffect starEffect24 = new HeartEffect(azerot.getEffectManager());
                starEffect24.setLocation(loc.add(0,1,0));
                starEffect24.setTargetPlayer(p);
                starEffect24.particle = Particle.REDSTONE;
                starEffect24.iterations = 2;
                starEffect24.duration = 20;
                starEffect24.start();
                break;
            case 24:
                HelixEffect starEffect25 = new HelixEffect(azerot.getEffectManager());
                starEffect25.setLocation(loc.add(0,1,0));
                starEffect25.setTargetPlayer(p);
                starEffect25.particle = Particle.REDSTONE;
                starEffect25.color = Color.BLACK;
                starEffect25.radius = 3;
                starEffect25.rotation = 20;
                starEffect25.iterations = 1;

                starEffect25.duration = 50;
                starEffect25.start();
                break;
            case 25:
                HillEffect starEffect26 = new HillEffect(azerot.getEffectManager());
                starEffect26.setLocation(loc.add(0,1,0));
                starEffect26.setTargetPlayer(p);
                starEffect26.particle = Particle.REDSTONE;
                starEffect26.iterations = 2;
                starEffect26.duration = 20;
                starEffect26.start();
                break;
            case 26:
                IconEffect starEffect27 = new IconEffect(azerot.getEffectManager());
                starEffect27.setLocation(loc.add(0,1,0));
                starEffect27.setTargetPlayer(p);
                starEffect27.particle = Particle.REDSTONE;
                starEffect27.duration = 20;
                starEffect27.start();
                break;
            case 27:
                ImageEffect starEffect28 = new ImageEffect(azerot.getEffectManager());
                starEffect28.setLocation(loc.add(0,1,0));
                starEffect28.setTargetPlayer(p);
                starEffect28.particle = Particle.REDSTONE;
                starEffect28.iterations = 2;
                starEffect28.duration = 20;
                starEffect28.start();
                break;
            case 28:
                JumpEffect starEffect29 = new JumpEffect(azerot.getEffectManager());
                starEffect29.setLocation(loc.add(0,1,0));
                starEffect29.setTargetPlayer(p);
                starEffect29.iterations = 2;
                starEffect29.duration = 20;
                starEffect29.start();
                break;
            case 29:
                LineEffect starEffect30 = new LineEffect(azerot.getEffectManager());
                starEffect30.setLocation(loc.add(0,1,0));
                starEffect30.setTargetPlayer(p);
                starEffect30.particle = Particle.REDSTONE;
                starEffect30.iterations = 2;
                starEffect30.duration = 20;
                starEffect30.start();
                break;
            case 30:
                LoveEffect starEffect31 = new LoveEffect(azerot.getEffectManager());
                starEffect31.setLocation(loc.add(0,1,0));
                starEffect31.setTargetPlayer(p);
                starEffect31.particle = Particle.REDSTONE;
                starEffect31.iterations = 2;
                starEffect31.duration = 20;
                starEffect31.start();
                break;
            case 31:
                ModifiedEffect starEffect32 = new ModifiedEffect(azerot.getEffectManager());
                starEffect32.setLocation(loc.add(0,1,0));
                starEffect32.setTargetPlayer(p);
                starEffect32.iterations = 2;
                starEffect32.duration = 20;
                starEffect32.start();
                break;
            case 32:
                MusicEffect starEffect33 = new MusicEffect(azerot.getEffectManager());
                starEffect33.setLocation(loc.add(0,1,0));
                starEffect33.setTargetPlayer(p);
                starEffect33.particle = Particle.REDSTONE;
                starEffect33.iterations = 2;
                starEffect33.duration = 20;
                starEffect33.start();
                break;
            case 33:
                ParticleEffect starEffect34 = new ParticleEffect(azerot.getEffectManager());
                starEffect34.setLocation(loc.add(0,1,0));
                starEffect34.setTargetPlayer(p);
                starEffect34.particle = Particle.REDSTONE;
                starEffect34.iterations = 2;
                starEffect34.duration = 20;
                starEffect34.start();
                break;
            case 34:
                PlotEffect starEffect35 = new PlotEffect(azerot.getEffectManager());
                starEffect35.setLocation(loc.add(0,1,0));
                starEffect35.setTargetPlayer(p);
                starEffect35.particle = Particle.REDSTONE;

                starEffect35.iterations = 2;
                starEffect35.duration = 20;
                starEffect35.start();
                break;
            case 35:
                PyramidEffect starEffect36 = new PyramidEffect(azerot.getEffectManager());
                starEffect36.setLocation(loc.add(0,1,0));
                starEffect36.setTargetPlayer(p);
                starEffect36.particle = Particle.REDSTONE;

                starEffect36.iterations = 2;
                starEffect36.duration = 20;
                starEffect36.start();
                break;
            case 36:
                ShieldEffect starEffect37 = new ShieldEffect(azerot.getEffectManager());
                starEffect37.setLocation(loc.add(0,1,0));
                starEffect37.setTargetPlayer(p);
                starEffect37.particle = Particle.REDSTONE;
                starEffect37.iterations = 2;
                starEffect37.duration = 20;
                starEffect37.start();
                break;
            case 37:
                SkyRocketEffect starEffect38 = new SkyRocketEffect(azerot.getEffectManager());
                starEffect38.setLocation(loc.add(0,1,0));
                starEffect38.setTargetPlayer(p);
                starEffect38.type = EffectType.INSTANT;
                starEffect38.iterations = 2;
                starEffect38.duration = 20;
                starEffect38.start();
                break;
            case 38:
                SmokeEffect starEffect39 = new SmokeEffect(azerot.getEffectManager());
                starEffect39.setLocation(loc.add(0,1,0));
                starEffect39.setTargetPlayer(p);
                starEffect39.particle = Particle.REDSTONE;
                starEffect39.iterations = 2;
                starEffect39.duration = 20;
                starEffect39.start();
                break;
            case 39:
                SoundEffect starEffect40 = new SoundEffect(azerot.getEffectManager());
                starEffect40.setLocation(loc.add(0,1,0));
                starEffect40.setTargetPlayer(p);
                starEffect40.type = EffectType.INSTANT;
                starEffect40.iterations = 2;
                starEffect40.duration = 20;
                starEffect40.start();
                break;
            case 40:
                SphereEffect starEffect41 = new SphereEffect(azerot.getEffectManager());
                starEffect41.setLocation(loc.add(0,1,0));
                starEffect41.setTargetPlayer(p);
                starEffect41.particle = Particle.REDSTONE;
                starEffect41.iterations = 2;
                starEffect41.duration = 20;
                starEffect41.start();
                break;
            case 41:
                SquareEffect starEffect42 = new SquareEffect(azerot.getEffectManager());
                starEffect42.setLocation(loc.add(0,1,0));
                starEffect42.setTargetPlayer(p);
                starEffect42.particle = Particle.REDSTONE;
                starEffect42.iterations = 2;
                starEffect42.duration = 20;
                starEffect42.start();
                break;
            case 42:
                StarEffect starEffect43 = new StarEffect(azerot.getEffectManager());
                starEffect43.setLocation(loc.add(0,1,0));
                starEffect43.setTargetPlayer(p);
                starEffect43.particle = Particle.REDSTONE;
                starEffect43.iterations = 2;
                starEffect43.duration = 20;
                starEffect43.start();
                break;
            case 43:
                TextEffect starEffect44 = new TextEffect(azerot.getEffectManager());
                starEffect44.setLocation(loc.add(0,1,0));
                starEffect44.setTargetPlayer(p);
                starEffect44.particle = Particle.REDSTONE;
                starEffect44.text = "Razrab krutoi";
                starEffect44.iterations = 2;
                starEffect44.duration = 50;
                starEffect44.start();
                break;
            case 44:
                TornadoEffect starEffect45 = new TornadoEffect(azerot.getEffectManager());
                starEffect45.setLocation(loc.add(0,1,0));
                starEffect45.setTargetPlayer(p);
                starEffect45.tornadoParticle = Particle.REDSTONE;
                starEffect45.cloudParticle = Particle.CLOUD;
                starEffect45.iterations = 2;
                starEffect45.duration = 20;
                starEffect45.start();
                break;
            case 45:
                TraceEffect starEffect46 = new TraceEffect(azerot.getEffectManager());
                starEffect46.setLocation(loc.add(0,1,0));
                starEffect46.setTargetPlayer(p);
                starEffect46.particle = Particle.REDSTONE;
                starEffect46.visibleRange = 2;
                starEffect46.iterations = 2;
                starEffect46.duration = 200;
                starEffect46.start();
                break;
            case 46:
                TurnEffect starEffect47 = new TurnEffect(azerot.getEffectManager());
                starEffect47.setLocation(loc.add(0,3,0));
                starEffect47.setTargetPlayer(p);
                starEffect47.type = EffectType.INSTANT;
                starEffect47.particleCount = 50;
                starEffect47.iterations = 2;
                starEffect47.duration = 200;
                starEffect47.start();
                break;
            case 47:
                VortexEffect starEffect48 = new VortexEffect(azerot.getEffectManager());
                starEffect48.setLocation(loc.add(0,1,0));
                starEffect48.setTargetPlayer(p);
                starEffect48.particle = Particle.REDSTONE;
                starEffect48.iterations = 2;
                starEffect48.duration = 20;
                starEffect48.start();
                break;
            case 48:
                WarpEffect starEffect49 = new WarpEffect(azerot.getEffectManager());
                starEffect49.setLocation(loc.add(0,1,0));
                starEffect49.setTargetPlayer(p);
                starEffect49.particle = Particle.REDSTONE;

                starEffect49.iterations = 2;
                starEffect49.duration = 20;
                starEffect49.start();
                break;
            case 49:
                WaveEffect starEffect50 = new WaveEffect(azerot.getEffectManager());
                starEffect50.setLocation(loc.add(0,1,0));
                starEffect50.setTargetPlayer(p);
                starEffect50.particle = Particle.REDSTONE;
                starEffect50.cloudParticle = Particle.CLOUD;
                starEffect50.iterations = 2;
                starEffect50.duration = 20;
                starEffect50.start();
                break;

        }

    }
}
