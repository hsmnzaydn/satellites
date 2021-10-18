import os

def writeFile(fileName,code):
    with open(fileName+"PositionEntity.kt",'a') as file:
        file.write(code)

def createFile(fileName):
    os.system("touch "+fileName+"PositionEntity.kt")

def createFolder(folderName):
    os.system("mkdir "+folderName)

def moveFileToFolder(moveFile,targetFolder):
    os.system("mv "+moveFile+"PositionEntity.kt "+targetFolder)

def moveFolderToFolder(moveFolder,targetFolder):
    os.system("mv "+moveFolder+" "+targetFolder)


def addCodeToFile(filePath,code):
    fileCode= open(filePath+"PositionEntity.kt","r").read()
    fileArrayCode = fileCode.split("\n")
    fileArrayCode.insert(len(fileArrayCode)-1,"\n"+code)
    newCode = ""
    for a in fileArrayCode:
        newCode = newCode + a +"\n"
    with open(filePath+"PositionEntity.kt", 'w') as file:
        file.write(newCode)
      



