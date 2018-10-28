#!/bin/env python
# -*- coding: utf-8 -*-
import sys
import telnetlib
from time import sleep
import random

char = '.'
FILE = open('/Users/jesper/.emulator_console_auth_token', 'r')
AUTH_TOKEN = FILE.read()
FILE.close()

HOST = "127.0.0.1"
PORT = 5554
TIMEOUT = 10
LAT_SRC = 57.688113
LNG_SRC = 11.979645
LAT_DST = 57.685576
LNG_DST = 11.982821
SECONDS = 5

LAT_MAX_STEP = ((max(LAT_DST, LAT_SRC) - min(LAT_DST, LAT_SRC)) / SECONDS) * 2
LNG_MAX_STEP = ((max(LNG_DST, LNG_SRC) - min(LNG_DST, LNG_SRC)) / SECONDS) * 2

DIRECTION_LAT = 1 if LAT_DST - LAT_SRC > 0 else -1
DIRECTION_LNG = 1 if LNG_DST - LNG_SRC > 0 else -1

lat = LAT_SRC
lng = LNG_SRC

tn = telnetlib.Telnet(HOST, PORT, TIMEOUT)
tn.set_debuglevel(9)
tn.read_until("OK", 5)

tn.write("auth {0}\n".format(AUTH_TOKEN))
tn.read_until("OK", 5)

tn.read_until("OK", 5)

tn.write("geo fix {0} {1}\n".format( str(LNG_SRC).replace('.', char), str(LAT_SRC).replace('.', char)))

for i in range(SECONDS):
    lat += round(random.uniform(0, LAT_MAX_STEP), 4) * DIRECTION_LAT
    lng += round(random.uniform(0, LNG_MAX_STEP), 4) * DIRECTION_LNG

    #tn.read_until("OK", 5)
    tn.write("geo fix {0} {1}\n".format(str(lng).replace('.', char), str(lat).replace('.', char)))
    #tn.write("exit\n")
    sleep(1)

tn.write("geo fix {0} {1}\n".format(str(LNG_DST).replace('.', char), str(LAT_DST).replace('.', char)))
tn.write("exit\n")

print tn.read_all()

