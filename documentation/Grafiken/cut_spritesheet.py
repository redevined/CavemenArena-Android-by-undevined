#!/usr/bin/env python

import sys, os
from PIL import Image


def main(fname, folder = ".", factor = 1) :
	sprites = Image.open(fname)
	size = 32
	factor = int(factor)
	
	sprite_names = {
		(0, 0) : "idle_1.png",				(1, 0) : "idle_2.png",
		(0, 1) : "lost_1.png",				(1, 1) : "lost_2.png",
		(0, 2) : "won_1.png",				(1, 2) : "won_2.png",
		(0, 3) : "poke_1.png",				(1, 3) : "poke_2.png",
		(0, 4) : "poke_3.png",				(1, 4) : "poke_4.png",
		(0, 5) : "block_1.png",				(1, 5) : "block_2.png",
		(0, 6) : "block_3.png",				(1, 6) : "block_4.png",
		(0, 7) : "sharpen_1.png",			(1, 7) : "sharpen_2.png",
		(0, 8) : "sharpen_3.png",			(1, 8) : "sharpen_4.png",
		
		(0, 9) : "idle_sword_1.png",		(1, 9) : "idle_sword_2.png",
		(0, 10) : "lost_sword_1.png",		(1, 10) : "lost_sword_2.png",
		(0, 11) : "won_sword_1.png",		(1, 11) : "won_sword_2.png",
		(0, 12) : "poke_sword_1.png",		(1, 12) : "poke_sword_2.png",
		(0, 13) : "poke_sword_3.png",		(1, 13) : "poke_sword_4.png",
		(0, 14) : "block_sword_1.png",		(1, 14) : "block_sword_2.png",
		(0, 15) : "block_sword_3.png",		(1, 15) : "block_sword_4.png",
		(0, 16) : "sharpen_sword_1.png",	(1, 16) : "sharpen_sword_2.png",
		(0, 17) : "sharpen_sword_3.png",	(1, 17) : "sharpen_sword_4.png"
	}
	
	for y in range(18) :
		for x in range(2) :
			sprite = sprites.crop((x*size, y*size, (x+1)*size, (y+1)*size))
			sprite = sprite.resize((size*factor, size*factor))
			sprite.save(os.path.join(folder, sprite_names[x, y]))


if __name__ == "__main__" :
	try :
		main(*sys.argv[1:])
	except Exception :
		print "Usage: python cut_spritesheet.py [spritesheet] [output_path] [zoom_factor]"