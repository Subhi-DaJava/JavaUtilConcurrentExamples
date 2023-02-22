package org.java_juc.begginer_level.future_cf_executor_service_16.fc_supplyrun_thenaccept.single_thread;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        UserServiceSingleThread userServiceSingleThread = new UserServiceSingleThread();
        try {
            userServiceSingleThread.sendNotification().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
/*
Thread getAllUsers : ForkJoinPool.commonPool-worker-1
Thread filters Female : ForkJoinPool.commonPool-worker-1
Thread filters Salary greater than 90000 : ForkJoinPool.commonPool-worker-1
Thread extracts emails : ForkJoinPool.commonPool-worker-1
Thread sends emails : ForkJoinPool.commonPool-worker-1

Hi, you are invited to this event: fdufore6@sourceforge.net
Hi, you are invited to this event: kgerssama@godaddy.com
Hi, you are invited to this event: dbresson1k@gnu.org
Hi, you are invited to this event: afaveryear1q@harvard.edu
Hi, you are invited to this event: jromaines1v@jigsy.com
Hi, you are invited to this event: ccoie2k@tripadvisor.com
Hi, you are invited to this event: stordiffe2u@whitehouse.gov
Hi, you are invited to this event: cloadwick2x@list-manage.com
Hi, you are invited to this event: agiuron42@nbcnews.com
Hi, you are invited to this event: mmatanin49@forbes.com
Hi, you are invited to this event: jwoolstenholmes4d@economist.com
Hi, you are invited to this event: tglowacha4h@seesaa.net
Hi, you are invited to this event: vstobbes4o@narod.ru
Hi, you are invited to this event: pblanchet5a@angelfire.com
Hi, you are invited to this event: lrochford5w@scribd.com
Hi, you are invited to this event: kleece6f@nifty.com
Hi, you are invited to this event: dfelton74@t-online.de
Hi, you are invited to this event: tcroal78@ow.ly
Hi, you are invited to this event: mossulton7d@elpais.com
Hi, you are invited to this event: kivell7l@latimes.com
Hi, you are invited to this event: apointon8i@google.com.br
Hi, you are invited to this event: schicco8p@google.nl
Hi, you are invited to this event: aroset90@phpbb.com
Hi, you are invited to this event: rbrainsby91@homestead.com
Hi, you are invited to this event: rscorthorne9f@ovh.net
Hi, you are invited to this event: uperrin9p@narod.ru
Hi, you are invited to this event: amearnsa4@fc2.com
Hi, you are invited to this event: rsharkeybf@mac.com
Hi, you are invited to this event: lcescobg@nature.com
Hi, you are invited to this event: vbozworthbz@live.com
Hi, you are invited to this event: tshayc2@cnn.com
Hi, you are invited to this event: smattiatoce@unblog.fr
Hi, you are invited to this event: rlamlincp@mashable.com
Hi, you are invited to this event: cpanonscv@cornell.edu
Hi, you are invited to this event: bformillicw@google.co.jp
Hi, you are invited to this event: pmccarlichdr@flavors.me
Hi, you are invited to this event: hgearingdt@opensource.org
Hi, you are invited to this event: tfalconertaylordw@linkedin.com
Hi, you are invited to this event: caleavyfm@cbc.ca
Hi, you are invited to this event: jsperskifr@tiny.cc
Hi, you are invited to this event: gbennoegq@imageshack.us
Hi, you are invited to this event: sedsellh1@google.com.hk
Hi, you are invited to this event: tchasmoorhe@prweb.com
Hi, you are invited to this event: pwisherhp@360.cn
Hi, you are invited to this event: kwinslowi5@example.com
Hi, you are invited to this event: sformiglij0@mediafire.com
Hi, you are invited to this event: nharbarj6@sohu.com
Hi, you are invited to this event: mtisonjr@xing.com
Hi, you are invited to this event: ccoiejw@taobao.com
Hi, you are invited to this event: lottamjz@myspace.com
Hi, you are invited to this event: djandourekkh@skype.com
Hi, you are invited to this event: ybluschkekk@mapquest.com
Hi, you are invited to this event: ajoburnkt@toplist.cz
Hi, you are invited to this event: tsteckingskz@si.edu
Hi, you are invited to this event: tmallenderl3@yellowpages.com
Hi, you are invited to this event: hhebbronms@prlog.org
Hi, you are invited to this event: jscutternk@mapy.cz
Hi, you are invited to this event: lmulqueennx@sohu.com
Hi, you are invited to this event: csapshedo0@scientificamerican.com
Hi, you are invited to this event: gjacobso5@ed.gov
Hi, you are invited to this event: lbranchoi@cpanel.net
Hi, you are invited to this event: tdawltreyov@google.com.au
Hi, you are invited to this event: dplantp1@youku.com
Hi, you are invited to this event: jchaneyq2@jimdo.com
Hi, you are invited to this event: mrevittq9@multiply.com
Hi, you are invited to this event: mminilloqf@telegraph.co.uk
Hi, you are invited to this event: mlinnero@nasa.gov
 */