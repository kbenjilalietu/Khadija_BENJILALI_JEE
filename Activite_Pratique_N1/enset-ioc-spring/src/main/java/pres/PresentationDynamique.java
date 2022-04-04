package pres;
import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class PresentationDynamique {
    public static void main(String[] args) throws Exception
    {
        Scanner sc=new Scanner(new File("config.txt"));

        String daoClassName = sc.nextLine();
        Class cDoa = Class.forName(daoClassName);
        IDao dao = (IDao)cDoa.newInstance();//créer un objet de la classe

        String metierClassName = sc.nextLine();
        Class cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier)cMetier.newInstance();

        Method method = cMetier.getMethod("setDao", IDao.class);
        method.invoke(metier, dao);
        System.out.println("Résultat => "+ metier.calcul());
    }
}

