#!/bin/sh

topdir=`pwd`

cd /home/water/sipphone/submodules/externals/ffmpeg
if test -z "`git status | grep neon`" ; then
	echo "Applying patch to ffmpeg"
	cd $topdir
	patch -p0 < /home/water/linphone-android/patches/ffmpeg_scalar_product_remove_alignment_hints.patch
fi

cd /home/water/sipphone/submodules/libilbc-rfc3951 && ./autogen.sh && ./configure && make || ( echo "iLBC prepare stage failed" ; exit 1 )

cd /home/water/sipphone/submodules/externals/build/libvpx && ./asm_conversion.sh && cp *.asm *.h ../../libvpx/ || ( echo "VP8 prepare stage failed." ; exit 1 )

cd /home/water/sipphone/submodules/mssilk && ./autogen.sh && ./configure --host=arm-linux MEDIASTREAMER_CFLAGS=" " MEDIASTREAMER_LIBS=" " && cd sdk && make extract-sources || ( echo "SILK audio plugin prepare state failed." ; exit 1 )

# As a memo, the config.h for zrtpcpp is generated using the command
# cmake  -Denable-ccrtp=false submodules/externals/libzrtpcpp
