@ This file was created from a .asm file
@  using the ads2gas.pl script.
	.equ DO1STROUNDING, 0
@
@  Copyright (c) 2010 The WebM project authors. All Rights Reserved.
@
@  Use of this source code is governed by a BSD-style license
@  that can be found in the LICENSE file in the root of the source
@  tree. An additional intellectual property rights grant can be found
@  in the file PATENTS.  All contributing project authors may
@  be found in the AUTHORS file in the root of the source tree.
@


    .global vp8_sad8x8_neon 
	.type vp8_sad8x8_neon, function
    .global vp8_sad8x16_neon 
	.type vp8_sad8x16_neon, function
    .global vp8_sad4x4_neon 
	.type vp8_sad4x4_neon, function

   @ ARM
   @ 
   @ PRESERVE8

.text
.p2align 2
@ unsigned int vp8_sad8x8_c(
@    unsigned char *src_ptr,
@    int  src_stride,
@    unsigned char *ref_ptr,
@    int  ref_stride)

_vp8_sad8x8_neon:
	vp8_sad8x8_neon: @ PROC
    vld1.8          {d0}, [r0], r1
    vld1.8          {d8}, [r2], r3

    vld1.8          {d2}, [r0], r1
    vld1.8          {d10}, [r2], r3

    vabdl.u8        q12, d0, d8

    vld1.8          {d4}, [r0], r1
    vld1.8          {d12}, [r2], r3

    vabal.u8        q12, d2, d10

    vld1.8          {d6}, [r0], r1
    vld1.8          {d14}, [r2], r3

    vabal.u8        q12, d4, d12

    vld1.8          {d0}, [r0], r1
    vld1.8          {d8}, [r2], r3

    vabal.u8        q12, d6, d14

    vld1.8          {d2}, [r0], r1
    vld1.8          {d10}, [r2], r3

    vabal.u8        q12, d0, d8

    vld1.8          {d4}, [r0], r1
    vld1.8          {d12}, [r2], r3

    vabal.u8        q12, d2, d10

    vld1.8          {d6}, [r0], r1
    vld1.8          {d14}, [r2], r3

    vabal.u8        q12, d4, d12
    vabal.u8        q12, d6, d14

    vpaddl.u16      q1, q12
    vpaddl.u32      q0, q1
    vadd.u32        d0, d0, d1

    vmov.32         r0, d0[0]

    bx              lr

	.size vp8_sad8x8_neon, .-vp8_sad8x8_neon    @ ENDP

@============================
@unsigned int vp8_sad8x16_c(
@    unsigned char *src_ptr,
@    int  src_stride,
@    unsigned char *ref_ptr,
@    int  ref_stride)

_vp8_sad8x16_neon:
	vp8_sad8x16_neon: @ PROC
    vld1.8          {d0}, [r0], r1
    vld1.8          {d8}, [r2], r3

    vld1.8          {d2}, [r0], r1
    vld1.8          {d10}, [r2], r3

    vabdl.u8        q12, d0, d8

    vld1.8          {d4}, [r0], r1
    vld1.8          {d12}, [r2], r3

    vabal.u8        q12, d2, d10

    vld1.8          {d6}, [r0], r1
    vld1.8          {d14}, [r2], r3

    vabal.u8        q12, d4, d12

    vld1.8          {d0}, [r0], r1
    vld1.8          {d8}, [r2], r3

    vabal.u8        q12, d6, d14

    vld1.8          {d2}, [r0], r1
    vld1.8          {d10}, [r2], r3

    vabal.u8        q12, d0, d8

    vld1.8          {d4}, [r0], r1
    vld1.8          {d12}, [r2], r3

    vabal.u8        q12, d2, d10

    vld1.8          {d6}, [r0], r1
    vld1.8          {d14}, [r2], r3

    vabal.u8        q12, d4, d12

    vld1.8          {d0}, [r0], r1
    vld1.8          {d8}, [r2], r3

    vabal.u8        q12, d6, d14

    vld1.8          {d2}, [r0], r1
    vld1.8          {d10}, [r2], r3

    vabal.u8        q12, d0, d8

    vld1.8          {d4}, [r0], r1
    vld1.8          {d12}, [r2], r3

    vabal.u8        q12, d2, d10

    vld1.8          {d6}, [r0], r1
    vld1.8          {d14}, [r2], r3

    vabal.u8        q12, d4, d12

    vld1.8          {d0}, [r0], r1
    vld1.8          {d8}, [r2], r3

    vabal.u8        q12, d6, d14

    vld1.8          {d2}, [r0], r1
    vld1.8          {d10}, [r2], r3

    vabal.u8        q12, d0, d8

    vld1.8          {d4}, [r0], r1
    vld1.8          {d12}, [r2], r3

    vabal.u8        q12, d2, d10

    vld1.8          {d6}, [r0], r1
    vld1.8          {d14}, [r2], r3

    vabal.u8        q12, d4, d12
    vabal.u8        q12, d6, d14

    vpaddl.u16      q1, q12
    vpaddl.u32      q0, q1
    vadd.u32        d0, d0, d1

    vmov.32         r0, d0[0]

    bx              lr

	.size vp8_sad8x16_neon, .-vp8_sad8x16_neon    @ ENDP

@===========================
@unsigned int vp8_sad4x4_c(
@    unsigned char *src_ptr,
@    int  src_stride,
@    unsigned char *ref_ptr,
@    int  ref_stride)

_vp8_sad4x4_neon:
	vp8_sad4x4_neon: @ PROC
    vld1.8          {d0}, [r0], r1
    vld1.8          {d8}, [r2], r3

    vld1.8          {d2}, [r0], r1
    vld1.8          {d10}, [r2], r3

    vabdl.u8        q12, d0, d8

    vld1.8          {d4}, [r0], r1
    vld1.8          {d12}, [r2], r3

    vabal.u8        q12, d2, d10

    vld1.8          {d6}, [r0], r1
    vld1.8          {d14}, [r2], r3

    vabal.u8        q12, d4, d12
    vabal.u8        q12, d6, d14

    vpaddl.u16      d1, d24
    vpaddl.u32      d0, d1
    vmov.32         r0, d0[0]

    bx              lr

	.size vp8_sad4x4_neon, .-vp8_sad4x4_neon    @ ENDP

	.section	.note.GNU-stack,"",%progbits
