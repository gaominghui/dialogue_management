# -*- coding: utf-8 -*- 
import os 

root = r'D:\javaProject\QA_CIST\old'
rootNew = r'D:\javaProject\QA_CIST\new'
inFiles = os.listdir(root)
for file in inFiles:
	fName = os.path.join(root,file)
	outFName = os.path.join(rootNew,file)
	lines = list(open(fName))
	fOut = open(outFName,'w')
	for i in range(len(lines)):
		lines[i] = lines[i].strip().decode('utf8')
	for i in range(2,len(lines)):
		
	
		if len(lines[i])==0:
			continue
		if( u'访客' in lines[i] ):
			outline = 'c:' + lines[i+1] + '\n'
			fOut.write(outline.encode('utf8'))
			i+=2
		elif('sixisb' in lines[i] ):
			outline = 's:' + lines[i+1] + '\n'
			fOut.write(outline.encode('utf8'))
			i+=2
	fOut.close()