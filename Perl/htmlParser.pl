#! /usr/bin/perl

if (scalar @ARGV == 1)
{
    #read choosen users from file into an array
    open my $handle, '<', $ARGV[0] or die "Could not open $ARGV[0]";
    chomp(@choosenUsers = <$handle>);
    close  $handle;
}

my @fileContent = <STDIN>;

foreach $element(@fileContent)
{
    if ($element =~ m#<tbody><tr class="headerrow">.*</tr>#g)
    {
        $getHtmlTable = $element;
    }
}

@NamesScores = $getHtmlTable =~ m#<tr class="problemrow".*?<a.*?([0-9A-Za-z_]*?)">(.*?)(</a>.*?)(\d{1,}.\d\d)</td></tr>#g;

$i = 0;
while($i < scalar @NamesScores)
{                                                                                                                                                                               my $flag = 0;
    if ( scalar @choosenUsers > 0 )
    {
        foreach $element(@choosenUsers)
        {
            if($element eq $NamesScores[$i])
            {
                $flag = 1;
            }
        }
    }
    else
    {
        $flag = 1;
    }

    if($flag == 1)
    {
        print "\"". $NamesScores[$i + 1] . "\","; #name

        print  "\"" . $NamesScores[$i] . "\","; #nick

        $task = $NamesScores[$i + 2]; #categories
        @categoriesTasks = $task =~ m#(\d{1,}.\d\d|-)#g;

        $iterator = 0;
        while( $iterator < scalar @categoriesTasks)
        {
            $temp =  $categoriesTasks[$iterator];

            if($temp eq "-")
            {
                $temp =  "0,0";
            }

            $temp =~ tr/./,/;
            print "\"" . $temp . "\",";
            $iterator = $iterator + 1;
        }
        print "\"" . $NamesScores[$i + 3] . "\"\n"; #score

    }
    $i = $i + 4;
}