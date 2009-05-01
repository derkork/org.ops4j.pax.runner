#!/bin/sh
#
# Script to run Pax Runner, which starts OSGi frameworks with applications.
#
#

SCRIPTS=`readlink $0`
if [ "${SCRIPTS}" != "" ]
then
  SCRIPTS=`dirname $SCRIPTS`
else
  SCRIPTS=`dirname $0`
fi

exec java $JAVA_OPTS -cp $SCRIPTS/target/pax-runner-0.20.0-SNAPSHOT-jdk14.jar:. org.ops4j.pax.runner.daemon.DaemonLauncher "$@"
