#! /usr/bin/perl
use POSIX qw(strftime);

my $argumentsNumber = scalar(@ARGV);

$longName = 0;
$ownerName = 0;
$dirPath = ".";  #default: current dir

if($argumentsNumber <= 3)
{
    foreach my $argument(@ARGV)
    {
        if($argument eq "-l")
        {
        $longName = 1;
        }
        elsif($argument eq "-L")
        {
        $ownerName = 1;
        }
        else
        {
        $dirPath = $argument;
        }
    }
}

opendir my $dir, $dirPath or die "Directory can not be open!"; #current dir

@dircontent = readdir $dir;

closedir($dir);

foreach $fileName(sort @dircontent) 
{
    my $fileLocalization = "$dirPath/$fileName";
    @fileDetails = stat "$fileLocalization"; #stat in table context
    $tempName = substr($fileName, 0, 30); #cut if larger than 30
    $name = sprintf("% 30s", sort $tempName); #add spaces if shorter than 30
    
    print "$name";

    if($longName == 1) 
    {
        $tempSize = substr($fileDetails[7], 0, 10);
        $size = sprintf("% 10d", $tempSize);
        $modifyTime = strftime ("%Y-%m-%d %H:%M:%S" , localtime($fileDetails[9]));
        $filePathAntiSpaces = "'$fileLocalization'"; #quotes protect from expanded by the shell
        $permissions = `stat --format %A $filePathAntiSpaces`; #%A - access rights in human readable form 
        chomp($permissions); #remove last \n symbol in varible
        print "$size $modifyTime $permissions";
    }

    if($ownerName == 1)
    {
        @ownerDetails = stat "$ownerName";
        $uid = $ownerDetails[4];
        $owner = getpwuid($uid);
        print "\t$owner";
    }
    print "\n";
}
