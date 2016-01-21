package tars;

import tars.model.LifeEngine;

/**
 * Created by tim on 20.01.16.
 */



/* Небольшая справка по редактированию.
 *  у объекта m.en есть такие функции, которые тебе пригодятся, чтобы задавать параметры:
 *  m.en.nextGeneration(); - внутри объекта LifeEngine(здесь экземпляр - m.en , пользуйся им) есть поле, в котором
 *  происходит жизнь. Данный метод генерирует следущее поколение.
 *  m.en.setValue(int x,int y,String state); - задает состояние клетки с координатой (x,y). Чтобы сделать клетку живой
 *  третьим аргументом передается "alive". Чтобы сделать мертвой - "dead"
 *
 *  В этом же главном классе есть специально подготовленный метод, который выводит все поле. Ибо метод не статичен, я не
 *  могу использовать его(статичные методы могут обращаться к своим полям\методам только если они статичны), пришлось
 *  создавать экземпляр главного класса.
 *
 *  Вот пример вызова метода для вывода поля:
 *  m.showFieldOnConsole(m.en);
 *
 *  Вот и все. Также в строке "m.en = ne LifeEngine(20,20)" можешь изменить размер поля, изменив значение аргумента.
 *          */
public class Main {
    LifeEngine en;
    public static void main(String[] args){
        Main m = new Main();
        m.en = new LifeEngine(20,20);


    }
    void showFieldOnConsole(LifeEngine en){
        System.out.println("New step");
        for(int i = 0;i < en.getMaxSizeX();i++){
            for(int j = 0;j < en.getMaxSizeY();j++){
                if(en.getField()[i][j].equals("alive")){
                    System.out.print(en.getField()[i][j].charAt(0)+" | ");
                }else{
                    System.out.print("_ | ");
                }

            }
            System.out.printf("\n");

        }
    }
}
