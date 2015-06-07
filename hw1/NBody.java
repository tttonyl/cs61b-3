import java.io.*;
import java.util.Scanner;

public class NBody{
    public static Planet getPlanet(In in){
        double x = in.readDouble();
        double y = in.readDouble();
        double xVelocity = in.readDouble();
        double yVelocity = in.readDouble();
        double mass = in.readDouble();
        String img = in.readString();
        Planet planet = new Planet(x, y, xVelocity, yVelocity, mass, img);
        return planet;
    }

    public static void main(String[] args){
        double T, dt, radius;
        double time = 0.0;
        int num;
        String filename;
        
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        filename = args[2];

        In in  = new In(filename);
        num = in.readInt();
        radius = in.readDouble();
        StdDraw.setScale(-radius, radius);

        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++){
            planets[i] = getPlanet(in);
        }

        StdAudio.play("audio/2001.mid");

        while (time < T){
            StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
            for (int i = 0; i < num; i++){
                planets[i].update(dt);
                planets[i].draw();
            }
            StdDraw.show(10);
            time += dt;
        }

        StdOut.printf("%d\n", num);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < num; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].x, planets[i].y, planets[i].xVelocity, planets[i].yVelocity, planets[i].mass, planets[i].img);
        }
    }
}
