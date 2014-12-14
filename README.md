Where is Waldo? (preparing for plue 2)
======================================

To take a look at the code, just browse the [src](src/pos1_2ahif/prep_test2) directory.

Build the project yourself
-----------------------------

1. [Download a copy](https://github.com/pos1-2ahif/prep-test2/archive/master.zip)
2. Run gradle (it will create a [eclipse project](http://eclipse.org/) for you)
   * on Windows just double-click `gradlew.bat` 
   * on Linux or MacOS run `./gradlew` 
3. Import the project into eclipse
   * via: `File` - `Import...` - `General` - `Existing Projects into Workspace`

Hint: This works for [IntelliJ IDEA](https://www.jetbrains.com/idea/) as well (use: `File` - `Open...`), in case you prefer it over eclipse.

Noch Fragen?
------------

Wenn Sie noch Fragen zu dem Beispiel Code haben, können Sie ein [Issue](https://github.com/pos1-2ahif/prep-test2/issues?q=is%3Aissue) anlegen (bitte überprüfen Sie vorher, ob bereits jemand anderer ein entsprechendes Issue angelegt hat - _durchsuchen Sie bitte auch die bereits geschlossenen Issues!_).

Es gibt __KEINE GARANTIE__, dass auf Issues reagiert wird; wir bemühen uns aber, so viele wie möglich zu beantworten.

Aufgabe
-------

Waldo versteckt sich in einer Map! Ihre Aufgabe ist es ihn zu finden:

Das ist die Deklaration der `WhereIsWaldo` Klasse (schauen Sie sich insbesondere den Map Typ an!)

```
public final class WhereIsWaldo implements Map<Integer, WhereIsWaldo.HidingPlace>
```

`Integer` ist ein gewöhnlicher `int` z.B. `7`. Lassen Sie sich davon nicht irritieren.

`WhereIsWaldo.HidingPlace` ist ein Versteck, das Sie durchsuchen können:

```
public interface HidingPlace {
    Object search();
}
```

Beachten Sie, dass nicht hinter jedem ```Integer``` auch tatsächlich ein Versteck liegt.

Es kann z.B. sein dass es an der Stelle `27` überhaupt gar kein Versteck gibt (also auch keinen Eintrag in der ```Map```).

Verwenden Sie die `search` Methode um ein Versteck zu durchsuchen, Sie bekommen dabei ein `Object` retour.

Dabei kann es sich handeln um:

##### `Waldo` selbst

Wenn das Objekt vom Typ `WhereIsWaldo.Waldo` ist, haben Sie Ihn gefunden.

```
public interface Waldo {
}
```

##### Ein Hinweis, wo sich `Waldo` versteckt

```
public interface Hint {
}

public interface HintLeft extends Hint {
}

public interface HintRight extends Hint {
}
```
Sollten Sie einen `WhereIsWaldo.HintLeft` gefunden haben, heißt dass, das `Waldo` irgendwo links zu finden ist - aber sicher nicht rechts. (Links bedeutet, er versteckt sich bei einem kleineren Key).

Sollten Sie einen `WhereIsWaldo.HintRight` gefunden haben, heißt dass, das `Waldo` irgendwo rechts zu finden ist - also bei einem größeren Key.

##### Ein herkömmliches `Object`

Hier ist leider gar nichts über `Waldo` zu finden. Weitersuchen!

### Beispiel

-12 | -3 | 4 | 5 | 42 | 1337 | 9000
----|----|---|---|----|------|-----
`HintRight` | | `HintRight` | | `Waldo` | `HintLeft` | 

```Waldo``` versteckt sich bei ```42```.
Es gibt Hinweise bei ```-12```, ```4``` und ```1337```.
An der Stelle `-3` z.B. ist nichts besonderes zu finden, bloß ein herkömmliches ```Object```.
An der Stelle `1` ist z.B. gar kein Versteck, daher auch kein Eintrag in der ```Map```.

### Ihr Code

Implementieren Sie das Interface `WhereIsWaldo.Exercises`. 
Die Methode `findWaldo` bekommt eine `WhereIsWaldo` Instanz und soll den Key zurückliefern, an dem sich `Waldo` versteckt.

Eine passende ```main``` Methode ist bereits programmiert.
Verändern Sie die ```WhereIsWaldo``` Klasse __NICHT__!

Sie können davon ausgehen, dass es immer genau einen `Waldo` gibt und dass alle Hinweise korrekt sind und dass die Hinweise halbwegs regelmäßig über die ```Map``` verteilt sind.

Bedenken Sie, dass die Map prinzipiell beliebig groß sein kann. 
Achten Sie daher darauf, dass Ihre Suche halbwegs vernünftig sucht (d.h. die Hinweise benutzt, um Waldo zu finden).

```
public interface Exercises {
    int findWaldo(WhereIsWaldo whereIsWaldo);
}
```

Viel Erfolg!

### Tipps & Hilfestellungen

Scrollen Sie das Fenster _behutsam_ herunter um nicht zu viele Hinweise auf einmal zu sehen.

```





```

Besorgen Sie sich eine Liste aller Keys in der ```Map```, um herauszufinden, an welchen Keys sich ```Waldo``` prinzipiell verstecken kann. (Sehen Sie sich ```Map.keySet``` an).

```





```

Um die Hinweise verarbeiten zu können, brauchen Sie die Keys in einer geordneten Form. In einem ```Set``` ist die Ordnung nicht relevant. Sie sollten die Keys daher in einer ```List``` verwalten. In einer ```List``` ist die Ordnung der Elemente relevant. Suchen Sie nach einem geeigneten ```ArrayList``` Constructor.

```





```

Sie brauchen die Keys der Größe nach geordnet, sonst wissen Sie nichts mit den Hinweisen ```HintLeft``` und ```HintRight``` anzufangen (```HintLeft``` bedeutet kleinerer Key, ```HintRight``` bedeutet größerer Key).
Wenn Sie die Keys bereits in einer ```List``` haben, können Sie diese leicht sortieren. Schauen Sie sich ```Collections.sort``` an.

```





```

Sobald Sie einen Hinweis gefunden haben, können Sie den Suchraum eingrenzen. Stellen Sie sich vor Sie finden folgenden Hinweis in der ```Map```

... ca. 1000 Einträge links | 5443 | ca. 1000 Einträge rechts ...
----------------------------|------|-----------------------------
                            |```HintRight```|

Sie brauchen die ersten 1000 Einträge dann gar nicht mehr durchsuchen. Nutzen Sie das aus.

```





```

Da Sie nicht wissen, wo sich ```Waldo``` befindet, sollten Sie in der Mitte anfangen zu suchen, und hoffen dass Sie bald einen Hinweis finden. Sollten Sie einen finden, können Sie dann ca. die Hälfte aller Einträge ausschließen. In den verbleibenden Einträgen verfahren Sie dann wieder genau gleich. Fangen Sie an in der Mitte zu suchen, in der Hoffnung dass Sie bald einen Hinweis finden.

```





```

[Hier finden Sie eine komplette Lösung](https://github.com/pos1-2ahif/prep-test2/blob/solution/src/pos1_2ahif/prep_test2/Main.java)
