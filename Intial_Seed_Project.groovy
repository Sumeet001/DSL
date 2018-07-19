import ProjectDetails
import SCMDetails

def RootFolder = 'Client-FromDSL'
def MSBuildProfile='MSbuild_Default'
def BuildSolutionFile= 'Testing_App.sln'
def ToolsFolder = 'C:\\Tools\\'
def NugetCommand = "${ToolsFolder}nuget.exe restore "
def PowerShellBundleCommand ='.\\scripts\\MakeBundle_x86_Debug.ps1'
def VSTestFileLocation = "C:\\Program Files (x86)\\Microsoft Visual Studio\\2017\\Enterprise\\Common7\\IDE\\CommonExtensions\\Microsoft\\TestWindow\\vstest.console.exe "

def TestFiles = []


def TestDllsLocations= TestFiles.join(" ") 


folder(RootFolder)
{
    description('Folder containing projects for ' + RootFolder)
    
}

ProjectDetails[] projects = [
   
   
]

ProjectDetails testProject=projects[0]



job(testProject.JobID)
{
    scm(){
        git {
            remote{
              github(testProject.SCMInformation.GitHubProjectName)
              credentials(testProject.SCMInformation.GitHubCredentials)

            }
            branch(testProject.SCMInformation.GitHubBranch)
        }
    }
    triggers 
    {

    }
    steps {

        //execute nuget restore
        def restoreNuget= "${NugetCommand}  %WORKSPACE%\\${BuildSolutionFile} -ConfigFile nuget.config"
        batchFile(restoreNuget)
    }
    steps {
       // msBuild {
         //   msBuildInstallation(MSBuildProfile)
          //  buildFile(BuildSolutionFile)
            //passBuildVariables()
            //continueOnBuildFailure()
            //unstableIfWarnings()
        //}
    }
    steps {
        
        //run Tests
       def TestCommand= "${VSTestFileLocation} ${TestDllsLocations} /Enablecodecoverage /Platform:x86 /Logger:trx"
       batchFile(TestCommand)

    }
    steps{
        //run build log parser to get build errors
        
    }
    publishers {
        warnings {
            consoleParsers {
                consoleParser {
                    parserName('MSBuild')
                }
            }
        }
    }
    publishers{
        msTestPublisher {
            failOnError(true)
            testResultsFile('**/*.trx')

        }
    }
    

}
